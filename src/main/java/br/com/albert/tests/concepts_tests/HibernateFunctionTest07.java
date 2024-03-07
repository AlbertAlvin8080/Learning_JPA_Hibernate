package br.com.albert.tests.concepts_tests;

import br.com.albert.model.entity.ManyToOne_OneToMany.Comment;
import br.com.albert.model.entity.ManyToOne_OneToMany.Post;
import br.com.albert.persistence.ConnectionFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

public class HibernateFunctionTest07 {
    public static void main(String[] args) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();

            var post = new Post();
            post.setContent("Summa Blasphemia");
            var comment = new Comment();
            var comment2 = new Comment();
//            comment.setPost(post);
//            comment.setPost(new Post(1));
            comment.setPost(post);
            comment.setText("Detestatio Sacrorum");
            comment2.setPost(post);
            comment2.setText("THE COOM");
            post.setComments(List.of(comment, comment2));

            em.persist(post);
//            em.persist(comment);
//            em.persist(comment2);
            em.flush();

            System.out.println(em.find(Post.class, 1).getComments());

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
