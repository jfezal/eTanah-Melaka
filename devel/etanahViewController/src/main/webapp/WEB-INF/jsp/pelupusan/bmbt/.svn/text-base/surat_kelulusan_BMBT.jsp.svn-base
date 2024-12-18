<%-- 
    Document   : surat_kelulusan_BMBT
    Created on : Nov 3, 2012, 11:48:40 AM
    Author     : Afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
     function getHakmilikDetails(val){
        
        doBlockUI();
        var edit = $('#edit').val() ;
        var url = '${pageContext.request.contextPath}/pelupusan/suratKelulusanBMBT?searchHakmilik&idHakmilik='+val+'&edit='+edit;
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }
</script>
<s:form beanclass="etanah.view.stripes.pelupusan.SuratKelulusanBMBTActionBean">
    <s:messages/>
    <div class="subtitle">
        <fieldset  class="aras1" id="locate">
                <legend> 
                    Senarai Hakmilik Terlibat
                </legend> 
               
                <p>
                <label>Hakmiilik :</label>
                <s:select name="idHakmilik" id="idmohon" onchange="getHakmilikDetails(this.value)">
                       <c:forEach items="${actionBean.hakmilikPermohonanList}" var="line">
                         <s:option value="${line.id}">${line.hakmilik.idHakmilik}(${line.hakmilik.bandarPekanMukim.daerah.nama}-${line.hakmilik.bandarPekanMukim.nama})</s:option>
                     </c:forEach> 
                </s:select>
                   </p>
               
        </fieldset>
        <fieldset class="aras1">
            <p>A:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <b><u>Syarat Am:-</u></b></p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Tanah yang diberimilik ini adalah tertakluk kepada peruntukan VIII Akta Kanun Tanah Negara (Hakmilik Pulau Pinang dan Melaka) 1963.</p>
            <p>B:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <b><u>Syarat-syarat Hakmilik:-</u></b></p><br>
            <table border="0">
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>i)</td>
                    <td>Jenis Hakmilik</td>
                    <td>:</td>
                    <td>Pajakan Negeri Stratum / Geran Stratum</td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>ii)</td>
                    <td>Tempoh</td>
                    <td>:</td>
                    <td>Kekal</td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>iii)</td>
                    <td>Penjenisan</td>
                    <td>:</td>
                    <td>${actionBean.hakmilik.kegunaanTanah.nama}</td>
                </tr>
            </table><br>
            <p>C:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u><b>Syarat Nyata:-</b></u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : ${actionBean.hakmilik.syaratNyata.syarat}</p>
            <p>D:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u><b>Sekatan Kepentingan:-</b></u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : ${actionBean.hakmilik.sekatanKepentingan.sekatan} </p><br><br>

            <%--<center><s:button name="simpan" id="save" class="btn" value="Simpan" onclick="doSubmit(this.form, this.name, 'page_div')"/></center>--%>


        </fieldset>


    </div>

</s:form>

