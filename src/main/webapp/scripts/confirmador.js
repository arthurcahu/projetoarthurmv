/**
 * Confirmação de exclusão de um participante 
*@author Arthur Cahú
 */

function confirmar(idcon){
	let resposta = confirm("CONFIRMA A EXCLUSÃO DO PARTICIPANTE?")
	if (resposta === true) {
		window.location.href = "delete?idcon=" + idcon
	}
	
}