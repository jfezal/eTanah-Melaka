<%-- 
    Document   : maklumat_tanah_dipohonV2CarianHakmilik
    Created on : May 27, 2013, 3:45:43 PM
    Author     : afham
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CARIAN HAKMILIK</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">
    $(document).ready( function() {

//        maximizeWindow();

        var len = $(".daerah").length;

        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/hakmilik?hakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }
        
        if($('#kodBPM').val() != null){
            filterKodSeksyen();
        }
    });

    function popup(id){
        window.open("${pageContext.request.contextPath}/common/maklumat_hakmilik_single?popup&idSyarat="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

    function simpan(){
        var idHakmilik = $('#idHakmilik').val();
        var statusPage = $('#statusPage').val();
          
        var url = '${pageContext.request.contextPath}/pelupusan/pihak_kepentingan?simpan&idHakmilik='+idHakmilik+'&statusPage='+statusPage;
        $.get(url,
        function(data){
            $('#page_div').html(data);
            self.opener.refreshlptn();
            self.close();
        },'html');          
    }
    function cariHakmilik(f){
        //            alert('aa');
        var statusPage = $('#statusPage').val();
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/pihak_kepentingan?cariHakmilik&statusPage='+statusPage,q,
        function(data){
            $('#page_div').html(data);
            //                self.opener.refreshlptn();
        }, 'html');         
    }
        
    function refreshpage()
        {
        self.close();
        opener.refreshV2MaklumatTanah();

        } 
    function selectRadioBox()
    {
        //                alert("test");
        var e= $('#sizeKod').val();
        //        alert(e);
        //                alert(document.frm.radiomate.value);
        var cnt=0;
        var data = new Array() ;

        for(cnt=0;cnt<e;cnt++)
        {
            //                     alert("test1
            if(e=='1'){
                if(document.frm.radiomate.checked) {
                    //                     alert(document.frm.checkmate[cnt].value) ;
                    data[cnt] = document.frm.radiomate.value ;
                    //                     alert(data[cnt])
                }
            }
            else{
                if(document.frm.radiomate[cnt].checked) {
                    //                     alert(document.frm.checkmate[cnt].value) ;
                    data[cnt] = document.frm.radiomate[cnt].value ;
                    //                     alert(data[cnt])
                }
                else{
                    data[cnt] = "T" ;
                }
            }
        }
        if(confirm("Adakah anda pasti?")) {
            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3?simpanHakmilik&idHakmilik='+data ;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                alert("Rekod telah berjaya di masukkan") ;
                self.close();
                self.opener.refreshV2MaklumatTanah() ;
            },'html');
        }
    }
    function filterKodSeksyen() {
        var kodBPM = $("#kodBPM").val();
        $.post('${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3?senaraiKodSeksyen&kodBPM=' + kodBPM,
                function(data) {
                  if (data != '') {
                    $('#partialKodSeksyen').html(data);
//                    $.unblockUI();
                  }
                }, 'html');

      }
      
     function checkIdHakmilik(val){
         $.get("${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3?doCheckHakmilik&idHakmilik=" + val,
        function(data){
            if(data == '1'){
                 alert("Cukai untuk Hakmilik " + val + " telah dijelaskan!");
            }
             else if(data =='2'){       
                alert("Cukai untuk Hakmilik " + val + " masih belum dijelaskan!");  
                $('#radiomate').removeAttr('checked');
                
            } 
            else if (data == '3'){
                alert("Status hakmilik Provisional. Tiada Urusniaga / Kaveat Persendirian / Lien dibenarkan.");
                $('#radiomate').removeAttr('checked');
            }
            else if (data == '4'){
                alert("Hakmilik " + val + " telah dibatalkan! Sila pilih hakmilik lain.");
                $('#radiomate').removeAttr('checked');
            }
//            else if (data.charAt(0) == '4'){
//                <%--alert(data);--%>
//            	$("#hakmilik" + idxHakmilik).val("");
//                var str = "Hakmilik " + val + " telah dibatalkan.";
//                if (data.substring(2).length > 0) str += " Hakmilik sambungan ialah " + data.substring(2) + ".";
//                alert(str);
//            	$("#msg" + idxHakmilik).html(str);
//            } 
            else{
                alert("Unknown reply: " + data);
            }
        });
     } 
</script>

<s:form beanclass="etanah.view.stripes.pelupusan.maklumat_tanah.MaklumatTanahV2ActionBean" name="frm">
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:errors/>
    <s:messages/>

    <div class="subtitle">
        <table class="tablecloth" border="0" align="center">
            <tr>
                <td>Daerah :</td>
                <td>${actionBean.permohonan.cawangan.daerah.nama}</td>
            </tr>
            <tr>
                <td>Mukim/Pekan/Bandar :</td>
                <td><s:select name="kodBPM" id="kodBPM" onchange="filterKodSeksyen();">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.senaraiKodBPM}" label="nama" value="kod" />
                    </s:select></td>
            </tr>
            <tr>
                <td>Seksyen :</td>
                <td id="partialKodSeksyen"></td>
            </tr>
            <tr>
                <td>Id Hakmilik :</td>
                <td><s:text name="idHakmilikCari" id="idHakmilikCari" size="25" onkeyup="this.value=this.value.toUpperCase();"/></td>
            </tr>
            <tr>
                <td>No Lot/No PT :</td>
                <td><s:text name="noLot" id="noLot" size="25"/></td>
            </tr>
            <tr>
                <td colspan="2"><s:submit name="cariHakmilik" value="Cari" class="btn"/>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="refreshpage();"/></td>
            </tr>
        </table>
      <%--  <p>
            <label>&nbsp;</label>
            <font size="2" color="red"><b> atau</b></font>
        </p>
        <p>
            <label for="No Hakmilik">No Hakmilik :</label>
            <s:text name="noHM" id="noHM" size="25"/>
        </p> --%>

    </div>
    <div class="subtitle">
        <p>
            <s:hidden name="${actionBean.sizeKod}" id="sizeKod" value="${actionBean.sizeKod}"/>
            <display:table style="width:100%" class="tablecloth" name="${actionBean.listHakmilik}" pagesize="100" cellpadding="0" cellspacing="0" requestURI="/pelupusan/pihak_kepentingan" id="line">
                <display:column> <s:radio name="radiomate" id="radiomate" value="${line.idHakmilik}" onclick="checkIdHakmilik(this.value);"/></display:column>
                <display:column title="ID Hakmilik" property="idHakmilik"/>
                <display:column title="No Lot">
                    ${line.lot.nama} ${line.noLot} 
                </display:column>
                <display:column title="No Hakmilik" property="noHakmilik"/>
                <c:set var="i" value="${i+1}" />
            </display:table>
        </p>
        <p>
            <c:if test="${fn:length(actionBean.listHakmilik) > 0 }">
                <s:button class="btn" value="Pilih" name="pilih" id="pilih" onclick="selectRadioBox();"/>
            </c:if>
        </p>
    </div>
</s:form>
