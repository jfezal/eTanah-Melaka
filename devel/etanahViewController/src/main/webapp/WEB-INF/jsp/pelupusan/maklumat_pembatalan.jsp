<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf"%>



<s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPembatalanActionBean">
	<s:messages />
	<s:errors />
	<div class="subtitle" align="center">
	<fieldset class="aras1">
	
	<table>
		<tr>
			<td>Sebab Pembatalan : </td>
			<td><s:textarea name="sebabPembatalan" id="sebabPembatalan" rows="6" cols="100" class="normal_text"/></td>
		</tr>
		<tr>
			<td></td>
			<td><s:reset name="reset" value="Isi Semula" class="btn"/><s:button name="saveData" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></td>
		</tr>
	</table>
	</fieldset>
	</div>
</s:form>