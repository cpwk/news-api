package com.maidao.edu.homework.springbootdemo.api.faq.service;

import com.maidao.edu.homework.springbootdemo.api.faq.entity.FaqKeyword;
import com.maidao.edu.homework.springbootdemo.api.faq.entity.FaqWord;
import com.maidao.edu.homework.springbootdemo.api.faq.entity.SimpleFaq;
import com.maidao.edu.homework.springbootdemo.api.faq.model.Faq;
import com.maidao.edu.homework.springbootdemo.api.faq.qo.FaqQo;
import com.maidao.edu.homework.springbootdemo.api.faq.repository.IFaqRepository;
import com.maidao.edu.homework.springbootdemo.common.cache.CacheOptions;
import com.maidao.edu.homework.springbootdemo.common.cache.KvCacheFactory;
import com.maidao.edu.homework.springbootdemo.common.cache.KvCacheWrap;
import com.maidao.edu.homework.springbootdemo.common.entity.Constants;
import com.maidao.edu.homework.springbootdemo.common.exception.RepositoryException;
import com.maidao.edu.homework.springbootdemo.common.util.L;
import com.maidao.edu.homework.springbootdemo.common.util.StringUtils;
import com.sunnysuperman.kvcache.RepositoryProvider;
import com.sunnysuperman.kvcache.converter.BeanModelConverter;
import com.sunnysuperman.kvcache.converter.ListModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:FaqService
 * 类描述:机器人类，定义两个class,idea会自动在下边罗列出来，
 * 在只使用一次的情况下可以这样用，但不建议这样用，会使代码变乱
 **/

@Service
public class FaqService implements IFaqService {

    @Autowired
    private IFaqRepository faqRepository;


    @Autowired
    private KvCacheFactory kvCacheFactory;
    private KvCacheWrap<Byte, List<Faq>> faqsCache;
    private KvCacheWrap<Byte, List<FaqWord>> wordsCache;
    private KvCacheWrap<Integer, Faq> faqCache;

    @PostConstruct
    public void init() {

        faqCache = kvCacheFactory.create(new CacheOptions("faq", 1, Constants.CACHE_REDIS_EXPIRE),
                new RepositoryProvider<Integer, Faq>() {
                    @Override
                    public Faq findByKey(Integer key) throws RepositoryException {
                        return faqRepository.getOne(key);
                    }

                    @Override
                    public Map<Integer, Faq> findByKeys(Collection<Integer> keys) throws RepositoryException {
                        throw new UnsupportedOperationException("keys");
                    }
                }, new BeanModelConverter<Faq>(Faq.class));

        faqsCache = kvCacheFactory.create(new CacheOptions("faqs", 1, Constants.CACHE_REDIS_EXPIRE), new RepositoryProvider<Byte, List<Faq>>() {

            @Override
            public List<Faq> findByKey(Byte key) throws RepositoryException {
                if (key == Constants.STATUS_OK) {
                    return faqRepository.findAll(new FaqQo(Constants.STATUS_OK));
                } else {
                    return faqRepository.findAll(new FaqQo());
                }
            }

            @Override
            public Map<Byte, List<Faq>> findByKeys(Collection<Byte> keys) throws RepositoryException {
                throw new UnsupportedOperationException("keys");
            }

        }, new ListModelConverter<Faq>(Faq.class));

        wordsCache = kvCacheFactory.create(new CacheOptions("faq_words", 1, Constants.CACHE_REDIS_EXPIRE), new RepositoryProvider<Byte, List<FaqWord>>() {

            @Override
            public List<FaqWord> findByKey(Byte key) throws Exception {

                List<Faq> faqs = faqs(false);

                List<FaqWord> words = new ArrayList<FaqWord>();
                for (Faq faq : faqs) {
                    List<FaqKeyword> kws = faq.getKeywords();
                    for (FaqKeyword kw : kws) {
                        for (String word : kw.getWords()) {
                            words.add(new FaqWord(faq.getId(), kw.getWeight(), word));
                        }
                    }
                }
                return words;
            }

            @Override
            public Map<Byte, List<FaqWord>> findByKeys(Collection<Byte> keys) throws Exception {
                throw new UnsupportedOperationException("keys");
            }

        }, new ListModelConverter<FaqWord>(FaqWord.class));


    }

    @Override
    public List<Faq> faqs(boolean adm) {
        return faqsCache.findByKey(adm ? 0 : Constants.STATUS_OK);
    }

    @Override
    public SimpleFaq ask(String q) {

        List<Faq> faqs = faqsCache.findByKey(Constants.STATUS_OK);
        List<FaqWord> words = wordsCache.findByKey(Constants.STATUS_OK);

        Map<Integer, Integer> scores = new HashMap<>(faqs.size());

        for (FaqWord word : words) {
            if (q.indexOf(word.getWord()) >= 0) {
                Integer score = scores.get(word.getFaqId());
                score = score == null ? word.getWeight() : score + word.getWeight();
                scores.put(word.getFaqId(), score);
            }
        }

        if (scores.isEmpty()) {
            return simpleFaq();
        }

        Map<Integer, Integer> resultMap = sortMapByScore(scores);

        // 最多1条
        List<Faq> result = new ArrayList<>(Math.min(resultMap.size(), 1));
        for (Map.Entry<Integer, Integer> entry : resultMap.entrySet()) {
            L.info(entry.getValue() + " : " + entry.getKey() + " " + q);

            // 过滤较低分数答案,前三档关键词均未中标，其余档关键词单独中标不可作为决定性条件
            int score = entry.getValue();
            if (score > 7) {
                result.add(faq(entry.getKey()));
            }

        }

        if (result.size() == 0) {
            return simpleFaq();
        }

        return wrapFaq(result.get(0));
    }

    private SimpleFaq simpleFaq() {
        return new SimpleFaq("没有为您找到合适回答，请直接联系老师");
    }

    private SimpleFaq wrapFaq(Faq faq) {
        return new SimpleFaq(StringUtils.getRandStr(16), faq.getContent());

    }

    private Map<Integer, Integer> sortMapByScore(Map<Integer, Integer> oMap) {
        if (oMap == null || oMap.isEmpty()) {
            return null;
        }
        Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<Map.Entry<Integer, Integer>>(oMap.entrySet());
        Collections.sort(entryList, new MapValueComparator());

        Iterator<Map.Entry<Integer, Integer>> iter = entryList.iterator();
        Map.Entry<Integer, Integer> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }

    @Override
    public void save_faq(Faq faq) {
        Integer id = faq.getId();
        if (id == null || id == 0) {
            faq.setCreatedAt(System.currentTimeMillis());
            faqRepository.save(faq);
            clearWrap();
        } else {
            Faq of = faq(id);
            of.setTitle(faq.getTitle());
            of.setKeywords(faq.getKeywords());
            of.setContent(faq.getContent());
            of.setStatus(faq.getStatus());
            faqRepository.save(of);
            clearAll(id);
        }
    }

    @Override
    public void remove_faq(int id) {
        faqRepository.deleteById(id);
        clearAll(id);
    }

    @Override
    public Faq faq(int id) {
        return faqCache.findByKey(id);
    }

    private void clearAll(int id) {
        faqCache.remove(id);
        clearWrap();
    }

    private void clearWrap() {
        faqsCache.remove((byte) 0);
        faqsCache.remove(Constants.STATUS_OK);
        wordsCache.remove(Constants.STATUS_OK);
    }

}

class MapValueComparator implements Comparator<Map.Entry<Integer, Integer>> {
    @Override
    public int compare(Map.Entry<Integer, Integer> me1, Map.Entry<Integer, Integer> me2) {
        return me2.getValue() - me1.getValue();
    }
}