<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>


<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">
    function edit(id, baki){
        <%--alert(baki);--%>

        frm = this.form;
        self.opener.addRows(id, baki);
        edit1();
    }

    function edit1(){
        self.close();
    }
</script>
<div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Pindaan Kutipan</font>
                    </c:if>
                    <c:if test="${actionBean.kodNegeri ne 'melaka'}">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Pindahan Kutipan</font>
                    </c:if>
                </div>
            </td>
        </tr>
</table></div>
<s:form beanclass="etanah.view.stripes.hasil.PindaanKutipanActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Hasil Carian</legend>
            <p class=instr>Sila klik pada Nombor Akaun untuk memilih Nombor Akaun yang baru.</p>
            <div class="content" align="center">
                <display:table name="${actionBean.alist}" id="line" class="tablecloth">
                    <%--<display:column title="Pilih">
                        <div align="center"><s:checkbox name="chkbox" value="${line.noAkaun}" id="chkbox${line_rowNum}" class="chkbox"/></div>
                        <div align="center"><s:radio name="radioBtn" value="${line.noAkaun}" id="radioBtn" class="chkbox"/></div>
                    </display:column>--%>
                    <display:column  title="Nombor Akaun" >
                        <a href="#" onclick="edit('${line.noAkaun}','${line.baki}');return false;" id="close">${line.noAkaun}</a>
                    </display:column>
                    <%--<display:column title="ID Hakmilik" property="hakmilik.idHakmilik"/>--%>
                    <%--<display:column title="Nombor Akaun" property="noAkaun"/>--%>
                    <display:column title="Nama" property="pemegang.nama"/>
                    <display:column title="Tarikh Kemasukan" property="infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm aa}"/>
                    <display:column title="Amaun (RM)" property="baki" format="{0,number, 0.00}" style="text-align:right"/>
                </display:table>
            </div>
        </fieldset>
    </div>

    <div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td align="right">
                &nbsp;
                <%--<s:submit name="tutup" value="Pilih" class="btn" onclick="edit(this.form);"/>--%>
                <s:submit name="" value="Pilih" class="btn" id="close" style="display:none"/>
                <%--<s:reset name=" " value="Isi Semula" class="btn"/>--%>
            </td>
        </tr>
    </table></div>

</s:form>