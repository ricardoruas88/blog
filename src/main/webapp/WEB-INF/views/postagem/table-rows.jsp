<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:forEach var="postagem" items="${page.content}" varStatus="i">		
			<tr bgcolor='${i.count % 2 != 0 ? '#f1f1f1' : 'white'}'>
				<td>${postagem.id}</td>
				<td>${postagem.titulo}</td>
				<td>${postagem.permalink}</td>
				<td>
					<fmt:parseDate var="date" value="${postagem.dataPostagem}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>
					<fmt:formatDate value="${date}" type="both"/>
				</td>
				<td>${postagem.autor.nome}</td>
				<td>
					<c:forEach var="c" items="${postagem.categorias}">
						[ ${c.descricao} ]
					</c:forEach>
				</td>
				<td>					
					<c:url var="update" value="/postagem/update/${postagem.id}"/>
					<a href="${update}" title="Editar">&#9445</a>
					<c:url var="delete" value="/postagem/delete/${postagem.id}"/>
					<a href="${delete}" title="Excluir">&#9447</a>
				</td>
			</tr>
			</c:forEach>