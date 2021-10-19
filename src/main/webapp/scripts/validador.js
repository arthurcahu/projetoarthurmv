/**
 * Validação do formulário
*@author Arthur Cahú
 */

function validar() {
	let nome = frmAdicionar.nome.value
	let cpf = frmAdicionar.cpf.value
	if (nome === "") {
		alert ('PREENCHA O CAMPO NOME')
		frmAdicionar.nome.focus()
		return false
	} else if (cpf.length > 11 || cpf.length <11) {   
		alert ('O CPF precisa ter 11 caracteres')
		frmAdicionar.cpf.focus()
		return false	
	} else if (cpf === "") { 
		alert ('PREENCHA O CAMPO CPF')
		frmAdicionar.cpf.focus()
		return false
	} else if (cpf === "") { 
		alert ('PREENCHA O CAMPO CONTRIBUIÇÃO')
		frmAdicionar.contribuicao.focus()
		return false 
	} else { 
		document.forms["frmAdicionar"].submit()
	}
			
}
