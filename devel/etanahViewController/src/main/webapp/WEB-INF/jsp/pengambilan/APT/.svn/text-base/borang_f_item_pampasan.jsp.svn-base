<%--
    Document   : maklumat_hakmilik_pengambilan
    Created on : 12-Jan-2010, 18:31:55
    Author     : nordiyana
--%>

<%@page import="java.text.DecimalFormat"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<%
    DecimalFormat dcf = new DecimalFormat("#0.0000");
%>
<script type="text/javascript">
    $(document).ready(function() {


        var len = $(".daerah").length;

        for (var i = 0; i <= len; i++) {
            $('.hakmilik' + i).click(function() {
                window.open("${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikpengambilan?popup&idHakmilik=" + $(this).text(), "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600").focus();
            });
        }
    });

      function hapusA(frm, value1,value2) {
            var url = '${pageContext.request.contextPath}/pengambilan/borang_a_b?hapusA&idPermohonan='+value1+'&id='+value2;
            if(confirm("Adakah anda pasti untuk hapus dokumen?")){
                frm.action = url;
            frm.submit();
            }
        }
              function hapusB(frm, value1,value2) {
            var url = '${pageContext.request.contextPath}/pengambilan/borang_a_b?hapusB&idPermohonan='+value1+'&id='+value2;
            if(confirm("Adakah anda pasti untuk hapus dokumen?")){
                frm.action = url;
            frm.submit();
            }
        }

        function save(event, f) {
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url, q,
                    function(data) {
                        $('#page_div', opener.document).html(data);

                    }, 'html');

        }

//        function select(id) {
//            doBlockUI();
//            frm = document.form1;
//            var url = '${pageContext.request.contextPath}/pengambilan/common/borangA?kemaskiniBorangA&idPihak=' + id;
//            frm.action = url;
//            frm.submit();
//        }

        function popupItemPampasan(idMohon,idBorangPerPb) {
//            alert(idMH);

            window.open("${pageContext.request.contextPath}/pengambilan/borang_e_f_pampasan?popupItem&idPermohonan=" + idMohon +"&idborangperpb="+idBorangPerPb, "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
        }
        function popupBorangB(idMohon,idBorangPerPermohonanB) {
//            alert(idMH);

            window.open("${pageContext.request.contextPath}/pengambilan/borang_a_b?tambahBorangB&idPermohonan=" + idMohon+"&idBorangPerPermohonanB="+idBorangPerPermohonanB, "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
        }
            function doViewReport(v) {
        var randomnumber = Math.floor((Math.random()*100)+1);          
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, randomnumber, "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }
        
          function doPopupDetails(val) {
        var url = '${pageContext.request.contextPath}/common/maklumat_permohonan?view&idPermohonan=' + val +'&idHakmilik='+idHakmilik;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=768,scrollbars=yes");
    }



</script>

        <s:form beanclass="etanah.view.stripes.pengambilan.share.common.MaklumatBorangEPampasanActionBean" name="AB" id="AB">

    <s:messages/>
    <div class="subtitle displaytag">

        <fieldset class="aras1" id="locate">
            <legend>
                Perincian Maklumat

            </legend>

            <p align="center">

            <table class="tablecloth">
                <tr><th colspan="2">Maklumat Permohonan</th>
                    <td>
                    <p>
                            <label>    ID Permohonan :</label> ${actionBean.idPermohonan}
                        </p>
                        <p>
                            <label>Urusan :</label> ${actionBean.urusan}
                        </p>
                        <p>
                            <label>Tujuan Permohonan :</label>
                        </p>
                     
                    </td></tr>
                <tr>
                    <th colspan="2">Maklumat Borang</th><th>Maklumat Tuntutan/Borang F</th>
                
                <tr>
                <tr>
                    <th>Borang E</th>
                    <td><div class="content" align="left">
                        <p>
                            <label>    Di tandatangan oleh :</label>
                        </p>
                        
                        <p>
                            <label> Dokumen :</label><img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReport('${actionBean.e.dok.idDokumen}');" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen ${actionBean.e.dok.kodDokumen.nama}"/>
                    
                        </p>
                    </div>
                    </td>
                    <td width="60%">
                        <div>
                        <display:table class="tablecloth" name="${actionBean.e.listBorangPerPB}"
                                   cellpadding="0" cellspacing="0" requestURI="/pengambilan/penerimaan_notis_borang_BantahanMLK" id="line">
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Nama " sortable="true">${line.pb.nama}</display:column>  
                            <display:column title="Jumlah Item Tuntutan " sortable="true">${line.totalItem}</display:column>
                            <display:column title="Jumlah Tuntutan " sortable="true">${line.amaun}</display:column>
                            <display:column title="Kemaskini " sortable="true">  
                                <s:button name="tambahBorangA" id="save" value="Tambah" class="longbtn" onclick="popupItemPampasan('${actionBean.idPermohonan}','${line.pb.id}')"/>
                            </display:column>                            
                        </display:table>
                            
              
                        </div>
                    </td>
                </tr>
               
            </table>
                                     <s:hidden name="urusan"/>
                                     <s:hidden name="urlKembali"/>
                <br>                
                <a href='${pageContext.request.contextPath}/pengambilan/borang_e_f_pampasan?${actionBean.urlKembali}&idPermohonan=${actionBean.idPermohonan}'><s:button name="hantar" id="save" value="Kembali" class="longbtn"/></a> 
        </fieldset>

    </div>

</s:form>
