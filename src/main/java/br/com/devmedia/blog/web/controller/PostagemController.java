package br.com.devmedia.blog.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.devmedia.blog.entity.Postagem;
import br.com.devmedia.blog.service.CategoriaService;
import br.com.devmedia.blog.service.PostagemService;
import br.com.devmedia.blog.web.editor.CategoriaEditorSupport;

@Controller
@RequestMapping("postagem")
public class PostagemController {

	@Autowired
	private PostagemService postagemService;
	@Autowired
	private CategoriaService categoriaService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(
				List.class, 
				new CategoriaEditorSupport(List.class, categoriaService)
		);
	}
	
	@RequestMapping(value = "/ajax/page/{page}", method = RequestMethod.GET)
	public ModelAndView pagePostagens(@PathVariable("page") Integer pagina) {
		
		ModelAndView view = new ModelAndView("postagem/table-rows");
		
		Page<Postagem> page = postagemService.findByPagination(pagina - 1, 5);
		
		view.addObject("page", page);
		//view.addObject("urlPagination", "/postagem/page");
		
		return view;
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public ModelAndView preUpdate(@PathVariable("id") Long id, ModelMap model) {
		
		Postagem postagem = postagemService.findById(id);
		
		model.addAttribute("postagem", postagem);
		model.addAttribute("categorias", categoriaService.findAll());
		
		return new ModelAndView("postagem/cadastro", model);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id) {
		
		postagemService.delete(id);
		
		return "redirect:/postagem/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listPostagens(ModelMap model) {
		
		//model.addAttribute("postagens", postagemService.findAll());
		Page<Postagem> page = postagemService.findByPagination(0, 5);
		
		model.addAttribute("page", page);
		//model.addAttribute("urlPagination", "/postagem/page");
		
		return new ModelAndView("postagem/list", model);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("postagem") Postagem postagem) {
		
		postagemService.saveOrUpdate(postagem);
		
		return "redirect:/postagem/list";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView cadastro(@ModelAttribute("postagem") Postagem postagem) {
		ModelAndView view = new ModelAndView("postagem/cadastro");
		view.addObject("categorias", categoriaService.findAll());
		return view;
	}
}
