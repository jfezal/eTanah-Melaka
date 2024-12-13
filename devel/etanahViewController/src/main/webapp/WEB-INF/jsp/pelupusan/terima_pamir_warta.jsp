<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:form beanclass="etanah.view.stripes.pelupusan.TerimaPamirWartaActionBean">
    <s:messages/>
    <s:errors/>
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
		<td>Tarikh Terima Warta</td>
		<td><label> <s:text name="tarikhTerimaWarta" id="tarikhTerimaWarta" /> 
		<img src="../images/calendar.gif" width="22" height="17" /> </label></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>Tarikh Warta</td>
		<td><s:text name="tarikhWarta" id="tarikhWarta" /> 
		<img src="../images/calendar.gif" alt="" width="22" height="17" /></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>No. Warta</td>
		<td><s:text name="noWarta" id="noWarta" /> (0-9)</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>Lokasi Warta Dipamirkan</td>
		<td rowspan="2"><label> <s:textarea name="lokasiWartaDipamirkan" id="lokasiWartaDipamirkan" /> </label></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>
		<s:reset name="reset" value="Isi Semula" class="btn"/>
        <s:button name="saveData" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
        </td>
	</tr>
</table>
</fieldset>
</div>
</s:form>