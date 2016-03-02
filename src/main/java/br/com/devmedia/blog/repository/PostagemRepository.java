package br.com.devmedia.blog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devmedia.blog.entity.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	
	Page<Postagem> findAllByOrderByDataPostagemDesc(Pageable pageable);
	
	Postagem findByPermalink(String permalink);

	List<Postagem> findByCategoriasPermalink(String link);

	List<Postagem> findByAutorNome(String nome);

	Page<Postagem> findAllByCategoriasPermalinkOrderByDataPostagemDesc(Pageable pageable, String permalink);

	Page<Postagem> findAllByAutorIdOrderByDataPostagemDesc(Pageable pageable, Long id);

	Page<Postagem> findByTextoContainingIgnoreCaseOrderByDataPostagemDesc(String texto, Pageable pageable);

}
