package DAO;

import org.hibernate.Criteria;
import utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public abstract class GenericJPADAO<T> implements GenericDAO<T>{
    protected Class<T> persistentClass;

    public EntityManager getEm() {
        return JPAUtil.getEntityManager();
    }

    @Override
    public void adicionar(T entity) {
        getEm().merge(entity);
    }

    @Override
    public void deletar(T entity) {
        getEm().remove(getEm().merge(entity));
    }

    @Override
    public T consultar(Object id) {
        return getEm().find(persistentClass, id);
    }

    @Override
    public void alterar(T entity) {
        getEm().merge(entity);
    }

    @Override
    public List<T> listaPaginada(int pagina, int tamanhoPagina) {
        CriteriaQuery<T> cq = getEm()
                .getCriteriaBuilder()
                .createQuery(persistentClass);
        cq.from(persistentClass);
        return getEm().createQuery(cq)
                .setFirstResult(pagina*tamanhoPagina-tamanhoPagina)
                .setMaxResults(tamanhoPagina)
                .getResultList();
    }

    @Override
    public void adicionaListaFuncionarios(List<T> entityList) {
        for(T entity: entityList)
            getEm().merge(entity);
    }
}
