<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<table width="100%" bgcolor="green">
    <tr>
        <td width="50%" height="20" ><div style="float:left;" class="formtitle"></div></td>
        <td width="50%"height="20" ><div style="float:right;" class="formtitle"></div></td>
    </tr>
</table>

<s:form beanclass="etanah.view.stripes.KutipanHasilActionBean">
<s:hidden name="hakmilik.idHakmilik" />
<p>
<label>
    <div class="instr"><b>Langkah 1: Carian Nombor Hakmilik</b></div>
</label>
</p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian Cukai Tanah</legend>
            <p>
                <label>
                    <div class="instr" align="center">Medan bertanda <font color="red"><em>*</em></font> adalah mandatori</div>
                </label>
            </p>
            <p>
                <label>
                    <div class="instr" align="center">
                        <s:errors/></div>
                </label>
            </p>

            <%--<p>
                <label>Nombor Akaun <font color="red">*</font>:</label>
                <s:text name="akaun.noAkaun"/>
            </p>
            <div class="instr" align="center"><font color="black">ATAU</font></div>--%>
            <p>
                <label> <font color="red"><em>*</em></font> Nombor Hakmilik :</label>
                <s:text name="hakmilik.idHakmilik"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:reset name=" " value="Isi Semula" class="btn"/> &nbsp;
                <s:submit name="search" value="Cari" class="btn"/>
            </p>
        </fieldset>
    </div>
</s:form>
