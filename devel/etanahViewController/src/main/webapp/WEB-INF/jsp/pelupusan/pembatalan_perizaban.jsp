<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf"%>

<s:form beanclass="etanah.view.stripes.pelupusan.PembatalanPerizabanActionBean">
	<s:messages />
	<s:errors />
	<div class="subtitle" align="center">
	<fieldset class="aras1">

	<table width="100%" border="0">
		<tr>
			<th width="16%" scope="col">&nbsp;</th>
			<th width="14%" scope="col">&nbsp;</th>
			<th width="70%" scope="col">&nbsp;</th>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td nowrap="nowrap">Sebab Pembatalan</td>
			<td rowspan="2"><label> <s:textarea name="sebabPembatalan" id="sebabPembatalan"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><s:reset name="reset" value="Isi Semula" class="btn"/>
        <s:button name="saveData" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></td>
		</tr>
	</table>

	</fieldset>
	</div>
</s:form>