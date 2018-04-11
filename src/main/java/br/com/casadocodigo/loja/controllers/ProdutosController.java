package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProdutoDao;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	private FileSaver fileSaver;
	
	// Informar ao Spring para gerenciar O Spring fará a injeção
	@Autowired
	private ProdutoDao produtoDao;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ProdutoValidation());
	}

	@RequestMapping("/form")
	public ModelAndView form(Produto produto) {

		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());

		return modelAndView;
	}

	/* O method=RequestMethod.POST é para diferenciar do listar que tem a mesma
	 * URL, mas utiliza o metodo GET.
	 * 
	 * Dá erro se ordem dos atributos for diferente.
	 * @Valid Produto produto, BindingResult result
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView gravar(MultipartFile sumario, 
			@Valid Produto produto, 
			BindingResult result,
			RedirectAttributes redirectAttributes) {
				
	    if(result.hasErrors()){
	        return form(produto);
	    }

	    String path = fileSaver.write("arquivos-sumario", sumario);

	    produto.setSumarioPath(path);
	    
	    produtoDao.gravar(produto);
		
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso.");
		ModelAndView modelAndView = new ModelAndView("redirect:/produtos");

		return modelAndView;
	}

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listar() {	
		List<Produto> produtos = produtoDao.listar();

		ModelAndView modelAndView = new ModelAndView("produtos/lista"); 
		modelAndView.addObject("produtos", produtos);
		
		return modelAndView;
	}

	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") Integer id) {
	  ModelAndView modelAndView = new ModelAndView("produtos/detalhe");
	  Produto produto = produtoDao.find(id);
	  modelAndView.addObject("produto", produto);
	  return modelAndView;
	}

	
}