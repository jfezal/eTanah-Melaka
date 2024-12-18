<%-- 
    Document   : kumpulan_akaun_1
    Created on : Nov 8, 2013, 11:23:40 AM
    Author     : haqqiem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">
    function hapusAlhi(id) {
        if (confirm("Adakah pasti untuk hapus Nombor Akaun " + id + " ?")) {
            var url = '${pageContext.request.contextPath}/hasil/kumpulan_akaun?delete&idHakmilikRemove=' + id;
            $.get(url,
                    function(data) {
                        $('#refreshPage').html(data);
                    }, 'html');
        }
    }    
</script>
<div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Kumpulan TAG Akaun</font>
                </div>
            </td>
        </tr>
    </table></div>
<s:form name="kumpulanAhli" beanclass="etanah.view.stripes.hasil.KumpulanAkaunTAGBaruActionBean" id="kump_baru">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
    <fieldset class="aras1">
        <legend>Maklumat Kumpulan</legend>
        <p>
            <label><em>*</em>Nama Kumpulan :</label>
            <s:text name="tagAkaun.nama" size="30" maxlength="30" id="nama" onkeyup="this.value=this.value.toUpperCase();"/>
        </p>
        <p>
            <label>Catatan :</label>
            <s:textarea id="catat" name="tagAkaun.catatan" cols="40" rows="4" onkeyup="this.value=this.value.toUpperCase();"/>
        </p>        
    </fieldset>
    </div>
    <div class="content" align="center">
        <fieldset class="aras1">
               <legend>Maklumat Hakmilik</legend>
               <c:if test="${actionBean.pengguna.kodCawangan.kod eq actionBean.tagAkaun.cawangan.kod}">
                   <p align="center">
                       <s:submit name="updates" value="Tambah Hakmilik" class="longbtn"/> &nbsp;
                    </p>
               </c:if>
                <%--div align="left">
                   <font size="2" color="black">Petunjuk :</font>
                    <p>
                        <img src="${pageContext.request.contextPath}/images/not_ok.gif"/> - <font size="2" color="black">Hapus</font>
                        &nbsp;<b>|</b>&nbsp;
                        <img src="${pageContext.request.contextPath}/images/lock.gif"/> - <font size="2" color="black">Tidak Dibenarkan Hapus</font>
                    </p>
                </div--%>
               <display:table class="tablecloth" name="${actionBean.listAkaun}" cellpadding="0" 
                              cellspacing="0" id="line" requestURI="/hasil/kumpulan_akaun" pagesize="10000">
                   <display:column title="Bil" sortable="true"><div align="center">${line_rowNum}</div></display:column>
                   <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                       <display:column property="noAkaun" title="No. Akaun"/>
                   </c:if>
                   <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                   <display:column property="baki" title="Jumlah Perlu Bayar (RM)" class="${line_rowNum}" format="{0,number, #,###,##0.00}" style="width:100;text-align:right;"/>
                   <display:column property="hakmilik.infoAudit.tarikhMasuk" title="Tarikh Daftar" format="{0,date,dd/MM/yyyy hh:mm:ss a}"/>
                   <display:column title="Hapus" style="text-align:center">
                        <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/not_ok.gif'
                                 onclick="hapusAlhi('${line.noAkaun}');" onmouseover="this.style.cursor='pointer';" title="Hapus Hakmilik :${line.noAkaun}"/>
                    </display:column>
                </display:table>
        </fieldset>
    </div>
    <div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td align="right">
                <s:submit name="Step3" value="Simpan" class="btn"/>&nbsp;
                <s:submit name="Step1" value="Menu Utama" class="btn"/>
            </td>
        </tr>
    </table></div>
</s:form>