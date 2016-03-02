package br.com.devmedia.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.devmedia.blog.entity.Categoria;
import br.com.devmedia.blog.service.CategoriaService;

@Controller
@RequestMapping("categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
	public ModelAndView pageCategorias(@PathVariable("page") Integer pagina, 
									   @ModelAttribute("categoria") Categoria value) {
		
		ModelAndView view = new ModelAndView("categoria/cadastro");
		
		Page<Categoria> page = service.findByPagination(pagina - 1, 5);
		
		view.addObject("page", page);
		view.addObject("urlPagination", "/categoria/page");
		
		return view;
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public ModelAndView preUpdate(@PathVariable("id") Long id, ModelMap model) {
		
		Categoria categoria = service.findById(id);
		
		Page<Categoria> page = service.findByPagination(0, 5);
		
		model.addAttribute("categoria", categoria);
		
		//model.addAttribute("categorias", service.findAll());
		
		model.addAttribute("page", page);
		
		model.addAttribute("urlPagination", "/categoria/page");
		
		return new ModelAndView("categoria/cadastro", model);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return "redirect:/categoria/add";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("categoria") Categoria categoria) {
		
		service.saveOrUpdate(categoria);
		
		return "redirect:/categoria/add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView cadastro(@ModelAttribute("categoria") Categoria categoria) {
		ModelAndView view = new ModelAndView();
		
		Page<Categoria> page = service.findByPagination(0, 5);
		
		//view.addObject("categorias", service.findAll());
		view.addObject("page", page);
		
		view.addObject("urlPagination", "/categoria/page");
		
		view.setViewName("categoria/cadastro");
		
		return view;
	}
}
