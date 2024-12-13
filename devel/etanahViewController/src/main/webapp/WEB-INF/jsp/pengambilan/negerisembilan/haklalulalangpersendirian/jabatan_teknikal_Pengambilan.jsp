<%-- 
    Document   : Jabatan_Teknikal_Pengambilan
    Created on : Oct 11, 2011, 2:28:55 PM
    Author     : Murali
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript">
    
    $(document).ready( function() {        
        $('#buttontandatangan').hide();
        $('#rujuluarDetails1').hide();
    });
            
    function Tandatangan() {
        if($('#namapguna').val() != ""){
            $('#buttontandatangan').show();
        }
    }

    function ajaxLink(link, update) {
        <%--alert(link);--%>
        $.get(link, function(data) {
            $(update).html(data);
            $(update).show();
            $('#rujuluarDetails1').show();
        });
        return false;       
    }
   
    function search(index, idHakmilik){
        var url = '${pageContext.request.contextPath}/pengambilan/sedia_jabatan?kodAgensiPopup&index='+index+'&idHakmilik='+idHakmilik;
        <%--alert(url);--%>
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800,scrollbars=yes");
    }
    function search1(kod,idRujukan){
        //                alert("Kod Agensi :" + kod);
        //                alert("Id Rujukan :" + idRujukan);
        // alert("search:"+index);
        var url = '${pageContext.request.contextPath}/pengambilan/sedia_jabatan?edit&kod='+kod+'&idRujukan='+ idRujukan;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800,scrollbars=yes");
    }
    function removePermohonanRujukanLuar(idRujukan,row){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/sedia_jabatan?deleteRujukan&idRujukan='
                +idRujukan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }

    function refreshAgensi(){
        var url = '${pageContext.request.contextPath}/pengambilan/sedia_jabatan?refreshPage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.pengambilan.JabatanTeknikalPengambilanActionBean">
    <div id="mainDiv">
        <s:messages/>

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Hakmilik Permohonan</legend>

                <p align="center">
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0"
                                   requestURI="${pageContext.request.contextPath}/pengambilan/sedia_jabatan" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <%--<display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>--%>
                        <display:column title="ID Hakmilik">
                            <c:forEach items="${line.hakmilik.idHakmilik}" var="senarai">
                                <s:link beanclass="etanah.view.stripes.pengambilan.JabatanTeknikalPengambilanActionBean"
                                        event="rujuluarDetails" onclick="return ajaxLink(this, '#mainDiv');" >
                                    <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>
                                    <s:param name="idMh" value="${line.id}"/>
                                    <c:out value="${line.hakmilik.idHakmilik}"/>
                                </s:link>
                            </c:forEach>
                        </display:column>
                        <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    </display:table>

                    <br/><br/>
                <div id="rujuluarDetails1">
                    <div>
                        <s:hidden name="testing" id="testing"/><p><label>Ditandatangan Oleh :</label></p>
                        <s:select name="ditundatangan" id="namapguna" onchange="Tandatangan();">
                                                       <s:option label="Sila Pilih" value="" />                          
                                                       <c:forEach items="${actionBean.penggunaList}" var="i" >                              
                                                               <s:option value="${i.idPengguna}" >${i.jawatan} - ${i.nama}</s:option>
                                                           </c:forEach>
                                                   </s:select>
                            <div id="buttontandatangan" align="center">
                            <%--<s:text name="idMh" value="${actionBean.idMh}"/>--%>                            
                            <s:button name="simpanTandatangan" class="longbtn" value="SimpanTandatangan" onclick="doSubmit(this.form,this.name,'page_div')"/>                        
                        </div>
                        <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                        <s:hidden name="idMh" value="${line.id}"/>
                    </div>

                    <div class="content" align="center" id="rujuluarDetails">
                        <c:set value="0" var="i"/>
                        <display:table class="tablecloth" name="${actionBean.senaraiRujukanLuar}" cellpadding="0" cellspacing="0" id="var"
                                       requestURI="${pageContext.request.contextPath}/pengambilan/sedia_jabatan">

                            <display:column title ="Bil">${var_rowNum}
                                <s:hidden name="" class="${var_rowNum -1}" value="${var.idRujukan}"/>
                            </display:column>
                            <display:column  title ="Nama Jabatan/ADUN">
                                <u><a onclick="javascript:search1(${var.agensi.kod},${var.idRujukan})" onmouseover="this.style.cursor='pointer';" >${var.agensi.nama}</a></u>
                            </display:column>
                            <display:column title ="Catatan" property="catatan"/>
                            <display:column title ="Tarikh Hantar" ><s:format value="${var.tarikhDisampai}" formatPattern="dd/MM/yyyy"/></display:column>
                            <display:column title ="Jangka Masa(Hari)" value="14"/>
                            <display:column title ="Tarikh Jangka Terima"><s:format value="${var.tarikhDisampai}" formatPattern="dd/MM/yyyy"/></display:column>
                            <display:column title ="Hapus">
                                <img alt='Klik Untuk Hapus' border='0' align="middle" src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="removePermohonanRujukanLuar('${var.idRujukan}','${i}')"  onmouseover="this.style.cursor='pointer';"/>
                            </display:column>
                            <display:column title ="Edit">
                                <img alt='Klik Untuk Edit' border='0' align="middle" src='${pageContext.request.contextPath}/pub/images/ok.png'
                                     id='${line_rowNum}' onclick="javascript:search1(${var.agensi.kod},${var.idRujukan})" name="edit" onmouseover="this.style.cursor='pointer';"/>
                            </display:column>

                            <s:hidden name="idRujukan${i}" id="idRujukan${i}" value="${var.idRujukan}" />
                        </display:table>
                        <s:button name="add" id="tambah" value="Tambah" class="btn" onclick="javascript:search('${i}','${actionBean.idHakmilik}')"/>
                    </div>                    
                </div>
            </fieldset>
        </div>
    </div>
</s:form>
