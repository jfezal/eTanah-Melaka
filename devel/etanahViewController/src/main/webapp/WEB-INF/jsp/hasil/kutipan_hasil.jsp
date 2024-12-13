<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<%--<script type="text/javascript">
    $(document).ready(function() {
        $("#daftar").attr("disabled", "true");
        $("#hakmilik").blur(function(){
            frm = this.form;
            if($("#hakmilik").val().length ==0){
                $("#daftar").attr("disabled", "true");
            }else{
                $("#daftar").removeAttr("disabled");
            }
        });
    });
</script>--%>

<table width="100%" bgcolor="green">
    <tr>
        <td width="50%" height="20" ><div style="float:left;" class="formtitle"></div></td>
        <td width="50%"height="20" ><div style="float:right;" class="formtitle"></div></td>
    </tr>
</table>
<s:errors field="a"/>
<s:form beanclass="etanah.view.stripes.KutipanHasilActionBean">
    <%--<s:hidden name="hakmilik.idHakmilik" />--%>
    <%--<div><font color="black"><b>Langkah 1: Carian Nombor Hakmilik</b></font></div><br>--%>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian Cukai Tanah</legend>
            <p>
                <em><font color="black">Sila Masukkan Nombor Akaun atau Nombor Hakmilik</font></em>
            </p>

            <p>
                <label>Nombor Akaun :</label>
                <s:text name="akaun.noAkaun" size="25" maxlength="20"/>
            </p>

            <p>
                <label>Nombor Hakmilik :</label>
                <s:text name="hakmilik.idHakmilik" id="hakmilik" size="25" maxlength="20"/>
            </p>
            <br>
        </fieldset>
    </div>

    <table border="0" bgcolor="green" width="100%">
        <tr>
            <td width="50%" height="20" align="right">
                <s:submit name="bayranPukal" value="Bayaran Pukal" class="btn"/>&nbsp;
                <s:reset name=" " value="Isi Semula" class="btn"/>&nbsp;
                <s:submit name="displayList" id="daftar" value="Seterusnya" class="btn"/>
            </td>
        </tr>
    </table>
                        <%--<s:errors field="radio"/>
                    
    <c:if test="${actionBean.visible}">
        
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Hasil Carian</legend>

                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.list}" pagesize="4" cellpadding="0" cellspacing="0" requestURI="kutipanHasil" id="line">
                        <display:column title="Pilih"><div align="center"><s:radio name="rbt" value="${line_rowNum}" /></div></display:column>
                        <display:column title="No."><div align="center">${line_rowNum}</div></display:column>
                        <display:column property="infoAudit.tarikhMasuk" title="Tarikh" format="{0,date,dd/MM/yyyy}"/>
                        <display:column property="noAkaun" title="Nombor Akaun"/>
                        <display:column property="hakmilik.idHakmilik" title="Nombor Hakmilik"/>
                        <display:column property="baki" title="Amaun (RM)" format="{0,number, 0.00}"/>
                    </display:table>
                </div>
            </fieldset>
        </div>

        <table border="0" bgcolor="green" width="100%">
            <tr>
                <td align="right">
                    <s:submit name="next" value="  Terus  " class="btn" disabled="${actionBean.flag}"/>
                </td>
            </tr>
        </table>
    </c:if>--%>
</s:form>
