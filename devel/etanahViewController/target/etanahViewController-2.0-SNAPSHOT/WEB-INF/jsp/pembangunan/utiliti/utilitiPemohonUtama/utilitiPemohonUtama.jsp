<%-- 
    Document   : utilitiPemohonUtama
    Created on : Dec 3, 2015, 9:32:36 AM
    Author     : Hazwan
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    function addNewPemohon() {
        window.open("${pageContext.request.contextPath}/utiliti/utilitiPemohonUtama?pemohonPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1200,height=700,scrollbars=yes");
    }

    function remove(val) {
        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/utiliti/utilitiPemohonUtama?delete&idPermohonanPihak=' + val;
            $.get(url,
            function(data) {
                $('#page_div').html(data);
            }, 'html');
        }
    }   

    function pemohoneqPenyerah(val,radioButton) {
        if(radioButton == "Y"){
            alert("Pemohon Dan Penyerah Adalah Orang Yang Sama !!!");
        }
        
        if(radioButton == "T"){
            alert("Pemohon Dan Penyerah Adalah BUKAN Orang Yang Sama !!!");
        }
    
        var url = '${pageContext.request.contextPath}/utiliti/utilitiPemohonUtama?simpanPenyeraheqPemohon&idPermohonan=' + val +'&radio1='+radioButton;
        $.get(url,
        function(data) {
            $('#page_div').html(data);
        }, 'html');
            
    }
    
    function pemohonUtama(val,radioButton,idPihak,nama) {
        if(radioButton == "Y"){
            alert("Pemohon Utama ialah " + nama + " !!!");
        }
    
        var url = '${pageContext.request.contextPath}/utiliti/utilitiPemohonUtama?simpanPemohonUtama&idPermohonan=' + val 
            +'&radio1='+radioButton+'&idPihak='+idPihak;
        $.get(url,
        function(data) {
            $('#page_div').html(data);
        }, 'html');
            
    }

    function dopopup(idPihak,idPemohon,idM) {  
        window.open("${pageContext.request.contextPath}/utiliti/utilitiPemohonUtama?showEditPemohon&idPihak=" + idPihak + "&idPemohon="+idPemohon+ '&idPermohonan='+idM, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1100px,height=600px");
    }
    
    function removePemohon(val,id) {
        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/utiliti/utilitiPemohonUtama?deletePemohon&idPemohon=' + val + '&idPermohonan=' + id;
            $.get(url,
            function(data) {
                $('#page_div').html(data);
            }, 'html');
            alert("Maklumat Pemohon Tersebut Telah Dibatalkan");
        }
    }
    
    function refreshDiv(){
        var id = '${actionBean.idPermohonan}';
        var url = '${pageContext.request.contextPath}/utiliti/utilitiPemohonUtama?check&idPermohonan='+id ;
        $.get(url,
        function(data){
            $("#refresh").replaceWith($('#refresh', $(data)));
        },'html');
    }
    
    //    function refresh(nama,alamat1,alamat2,alamat3,alamat4,poskod,negeri,noPengenalan,noTel1){
    //        $('#nama').val(nama);
    //        $('#alamat1').val(alamat1); 
    //        $('#alamat2').val(alamat2); 
    //        $('#alamat3').val(alamat3); 
    //        $('#alamat4').val(alamat4); 
    //        $('#poskod').val(poskod); 
    //        $('#negeri').val(negeri); 
    //        $('#noPengenalan').val(noPengenalan);   
    //        $('#noTel1').val(noTel1);    
    //        alert("xx");
    //    }
    
</script>
<s:messages/>
<s:errors/>
<s:form beanclass="etanah.view.stripes.pembangunan.utiliti.utilitiPemohonUtamaActionBean">
    <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.idPermohonan}"/>

    <c:if test = "${actionBean.idMohonShow}"> 
        <div class="subtitle">
            <fieldset class="aras1">
                <br>
                <legend>Sila Masukkan ID Permohonan Untuk Carian Pemohon</legend>

                <label for="idPermohonan"><em>*</em>ID Permohonan :</label>                   
                <input type="text" name="idPermohonan" onkeyup="this.value=this.value.toUpperCase();"/>

                <br/><br/>
                <p align="center">
                    <s:submit name="checkPermohonan" value="Cari" class="btn"/>             
                </p>
            </fieldset>
        </div>
    </c:if>

    <br/>    
    <c:if test = "${!actionBean.idMohonShow}"> 
        <fieldset class="aras1">
            <legend>
                Pemohon Dan Penyerah 
            </legend>
            <div align ="center">
                <td><legend><b>Pemohon Dan Penyerah Adalah Orang Yang Sama ?  </b></legend></td>
                <br/>
                <td>
                    <s:radio name="radio1" value="Y" onclick="pemohoneqPenyerah('${actionBean.idPermohonan}','Y')"/><font color="#001848"> Ya </font> &nbsp;
                    <s:radio name="radio1" value="T" onclick="pemohoneqPenyerah('${actionBean.idPermohonan}','T')"/><font color="#001848"> Tidak </font>                   
                </td>

                <br/><br/>          
            </div>
        </fieldset>
        <br/>

        <fieldset class="aras1">
            <legend>
                Pemohon Utama (Untuk Kegunaan Surat) 
            </legend>
            <div class="content" align="center" id="refresh">
                <display:table class="tablecloth" name="${actionBean.allpemohonList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                    <display:column title="Pilihan" sortable="true" style="vertical-align:baseline">
                        <div align="center"><s:radio name="radio2" value="${line.nama}" onclick="pemohonUtama('${actionBean.idPermohonan}','Y','${line.pihak.idPihak}','${line.pihak.nama}')"/></div>
                    </display:column>
                    <display:column property="pihak.nama" title="Nama" style="vertical-align:baseline"/>
                    <display:column property="pihak.noPengenalan" title="No. Pengenalan" style="vertical-align:baseline"/>
                    <display:column title="Alamat" style="vertical-align:baseline">${line.pihak.alamat1}
                        <c:if test="${line.pihak.alamat2 ne null}"> , </c:if>
                        ${line.pihak.alamat2}
                        <c:if test="${line.pihak.alamat3 ne null}"> , </c:if>
                        ${line.pihak.alamat3}
                        <c:if test="${line.pihak.alamat4 ne null}"> , </c:if>
                        ${line.pihak.alamat4}</display:column>
                    <display:column property="pihak.poskod" title="Poskod" style="vertical-align:baseline"/>
                    <display:column property="pihak.negeri.nama" title="Negeri" style="vertical-align:baseline"/>
                    <display:column property="pihak.noTelefon1" title="No. Telefon" style="vertical-align:baseline"/>
                    <%--
                    <display:column title="Kemaskini" style="vertical-align:baseline">
                        <div align="center">                                  
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="dopopup('${line.pihak.idPihak}','${line.idPemohon}','${line.permohonan.idPermohonan}');
                                     return false;">
                        </div>
                    </display:column>
                    <display:column title="Hapus" style="vertical-align:baseline">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removePemohon('${line.idPemohon}','${line.permohonan.idPermohonan}')">
                        </div>
                    </display:column>
                    --%>
                </display:table>
            </div>

            <br/>
            <div align ="center">
                <s:submit class="btn" value="Kembali" name="reset"/>&nbsp;
            </div>
        </fieldset>
    </c:if>
</s:form>
