package org.acme.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.acme.model.TransparencyDataReport;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.Search;
import org.infinispan.commons.util.CloseableIterator;
import org.infinispan.query.dsl.QueryFactory;
import org.jboss.logging.Logger;

@ApplicationScoped
public class ESMAService {

    private static final Logger LOGGER = Logger.getLogger(ESMAService.class);

    @Inject
    RemoteCacheManager cacheManager;

    RemoteCache<String, TransparencyDataReport> cache;

    @PostConstruct
    private void init() {
        LOGGER.info("Getting esma cache");
        cache = cacheManager.getCache("esma");
    }

    public List<TransparencyDataReport> getById(String id) {

        QueryFactory qf = Search.getQueryFactory(cache);

        org.infinispan.query.dsl.Query<TransparencyDataReport> q = qf.create(
                "FROM esma.TransparencyDataReport where id = :id");

        q.setParameter("id", id);

        return q.maxResults(100).execute().list();

    }

    public List<TransparencyDataReport> getSome(int size) {

        List<TransparencyDataReport> retValue = new ArrayList<TransparencyDataReport>();

        CloseableIterator<Entry<String, TransparencyDataReport>> iterator = cache.entrySet().iterator();

        for (int i = 0; i < size && iterator.hasNext(); i++) {
        
            Entry<String, TransparencyDataReport> element = iterator.next();

            retValue.add(element.getValue());

        }

        iterator.close();

        return retValue;

    }

    public List<TransparencyDataReport> searchId(String id, int size) {
        QueryFactory qf = Search.getQueryFactory(cache);

        org.infinispan.query.dsl.Query<TransparencyDataReport> q = qf.create(
                "FROM esma.TransparencyDataReport WHERE id like :id");

        q.setParameter("id", id);

        return q.maxResults(size).execute().list();
    }

}
