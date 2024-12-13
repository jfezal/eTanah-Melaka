<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPenerimaanWartaActionBean">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">

        <fieldset class="aras1">
            <legend>Maklumat Penerimaan Warta</legend>


            <table width="100%" border="0">
                <tr>
                    <th width="3%" scope="col">&nbsp;</th>
                    <th width="12%" scope="col">&nbsp;</th>
                    <th width="85%" scope="col">&nbsp;</th>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>Tarikh Terima Warta</td>
                    <td><s:text id="tarikhTerimaWarta" name="tarikhTerimaWarta" class="datepicker" formatPattern="dd/MM/yyyy"/></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>Tarikh Warta</td>
                    <td><s:text id="tarikhWarta" name="tarikhWarta" class="datepicker" formatPattern="dd/MM/yyyy"/></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>Nombor Warta</td>
                    <td><s:text id="nomborWarta" name="nomborWarta"/></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>
                        <s:button name="saveData" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        <s:reset name="reset" value="Isi Semula" class="btn"/>
                    </td>
                </tr>
            </table>
        </fieldset >
    </div>
</s:form>
