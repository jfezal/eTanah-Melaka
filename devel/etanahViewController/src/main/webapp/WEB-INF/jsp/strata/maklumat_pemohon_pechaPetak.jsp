<%--
    Document   : maklumat_pemohon
    Created on : nov 27, 2013, 10:28:20 AM
    Author     : murali   
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function resetForm(){
        $('#nama').val('');
        $('#alamat1').val('');
        $('#alamat2').val('');
        $('#alamat3').val('');
        $('#alamat4').val('');
        $('#poskod').val('');
        $('#negeri').val('');
        $('#jenisPengenalan').val('');
        $('#nomborPengenalan').val('');
        document.getElementById("checkAlamat").checked =false;
        $('#suratAlamat1').val('');
        $('#suratAlamat2').val('');
        $('#suratAlamat3').val('');
        $('#suratAlamat4').val('');
        $('#suratPoskod').val('');
        $('#suratNegeri').val('');
        
    }
    function validation(){

        if($("#nama").val() == ""){
            alert('Sila masukan " nama " terlebih dahulu.');
            $("#nama").focus();
            return false;
        }else{
            return true;
        }
    }
    function copyAdd(){
        if($('input[name=checkAlamat]').is(':checked')){
            $('#suratAlamat1').val($('#alamat1').val());
            $('#suratAlamat2').val($('#alamat2').val());
            $('#suratAlamat3').val($('#alamat3').val());
            $('#suratAlamat4').val($('#alamat4').val());
            $('#suratPoskod').val($('#poskod').val());
            $('#suratNegeri').val($('#negeri').val());
        }else{
            $('#suratAlamat1').val('');
            $('#suratAlamat2').val('');
            $('#suratAlamat3').val('');
            $('#suratAlamat4').val('');
            $('#suratPoskod').val('');
            $('#suratNegeri').val('');
        }
    }
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if($('#jenisPengenalan').val()== 'B'){
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }
    }

    function validateNumber2(elmnt,content) {
        //if it is character, then remove it..

        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }

    }

    function removeNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }
    function noPengenalanhide(){
        if($('#jenisPengenalan').val() == 'B'){
            $('#nomborPengenalan').val("");
        }
    }
    function kemaskini(idPemohon){       
        $.get("${pageContext.request.contextPath}/strata/pechaPetak/kemasukan_pemohon?showPemohon&idPemohon="+idPemohon,
        function(data){
            $("#pemohon").show();
            $("#pemohon").html(data);                
        });
    }   
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.strata.PechaPetakPemohonActionBean" name="form1">
    <s:messages/>
    <s:errors/>

    <s:hidden name="idPihak"/>
    <c:if test="${!readOnly}">
        <div class="subtitle">
            <fieldset class="aras1">
                <p>
                    <label>ID Hakmilik :</label>
                <table><tr><td>
                            <c:forEach items="${actionBean.hakmilikPermohonanListBaru}" var="line">
                                <c:if test="${line.hakmilik.idHakmilik ne null}">${line.hakmilik.idHakmilik}</c:if> <br />
                                <c:if test="${line.hakmilik.idHakmilik eq null}"> Tiada Data </c:if>                            
                            </c:forEach>
                        </td></tr></table>
                </p><br />

                <div align="center">
                    <display:table class="tablecloth" name="${actionBean.listPemohon}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                        <display:column title="Bil" sortable="true"><center>${line_rowNum}</center></display:column>
                        <display:column title="Nama" class="nama"><center>${line.nama}</center></display:column>
                        <display:column title="Jenis Pengenalan"><center>${line.jenisPengenalan.nama}</center></display:column>
                        <display:column title="Nombor Pengenalan"><center>${line.noPengenalan}</center></display:column>
                        <display:column title="Alamat" ><center>
                                <c:if test="${line.pihak.alamat1 != null && line.pihak.alamat1 != ''}">${line.pihak.alamat1},</c:if> 
                                <c:if test="${line.pihak.alamat2 != null && line.pihak.alamat2 != ''}">${line.pihak.alamat2},</c:if> 
                                <c:if test="${line.pihak.alamat3 != null && line.pihak.alamat3 != ''}">${line.pihak.alamat3},</c:if> 
                                <c:if test="${line.pihak.alamat4 != null && line.pihak.alamat4 != ''}">${line.pihak.alamat4},</c:if> 
                            </center>                        
                        </display:column>
                        <display:column title="Poskod"><center>${line.pihak.poskod}</center></display:column>
                        <display:column title="Negeri"><center>${line.pihak.negeri.nama}</center></display:column>
                        <display:column title="Surat Alamat" ><center>
                                <c:if test="${line.pihak.suratAlamat1 != null && line.pihak.suratAlamat1 != ''}">${line.pihak.suratAlamat1},</c:if> 
                                <c:if test="${line.pihak.suratAlamat2 != null && line.pihak.suratAlamat2 != ''}">${line.pihak.suratAlamat2},</c:if> 
                                <c:if test="${line.pihak.suratAlamat3 != null && line.pihak.suratAlamat3 != ''}">${line.pihak.suratAlamat3},</c:if> 
                                <c:if test="${line.pihak.suratAlamat4 != null && line.pihak.suratAlamat4 != ''}">${line.pihak.suratAlamat4},</c:if> 
                            </center>                        
                        </display:column>
                        <display:column title="Surat Poskod"><center>${line.pihak.suratPoskod}</center></display:column>
                        <display:column title="Surat Negeri"><center>${line.pihak.suratNegeri.nama}</center></display:column>
                        <display:column title="Kemaskini"><center><img alt='Klik Untuk Kemaskini Pemohon' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                     id="" title="Klik Untuk Kemaskini Pemohon" onclick="kemaskini('${line.idPemohon}');return false;" onmouseover="this.style.cursor='pointer';"/></center>
                            </display:column>                        
                        </display:table>                    
                </div>                
                <br /><br /><br /><br />
                <div id="pemohon">
                </div>               
            </fieldset>
        </div>
    </c:if>


    <c:if test="${readOnly}">
        <div class="subtitle">
            <fieldset class="aras1">
                <p>
                    <label>ID Hakmilik :</label>
                   <table><tr><td>
                            <c:forEach items="${actionBean.hakmilikPermohonanListBaru}" var="line">
                                <c:if test="${line.hakmilik.idHakmilik ne null}">${line.hakmilik.idHakmilik}</c:if> <br />
                                <c:if test="${line.hakmilik.idHakmilik eq null}"> Tiada Data </c:if>                            
                            </c:forEach>
                        </td></tr></table>
                </p><br />

                <div align="center">
                    <display:table class="tablecloth" name="${actionBean.listPemohon}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                        <display:column title="Bil" sortable="true"><center>${line_rowNum}</center></display:column>
                        <display:column title="Nama" class="nama"><center>${line.pihak.nama}</center></display:column>
                        <display:column title="Jenis Pengenalan"><center>${line.pihak.jenisPengenalan.nama}</center></display:column>
                        <display:column title="Nombor Pengenalan"><center>${line.pihak.noPengenalan}</center></display:column>
                        <display:column title="Alamat" ><center>
                                <c:if test="${line.pihak.alamat1 != null && line.pihak.alamat1 != ''}">${line.pihak.alamat1},</c:if> 
                                <c:if test="${line.pihak.alamat2 != null && line.pihak.alamat2 != ''}">${line.pihak.alamat2},</c:if> 
                                <c:if test="${line.pihak.alamat3 != null && line.pihak.alamat3 != ''}">${line.pihak.alamat3},</c:if> 
                                <c:if test="${line.pihak.alamat4 != null && line.pihak.alamat4 != ''}">${line.pihak.alamat4},</c:if> 
                            </center>                        
                        </display:column>
                        <display:column title="Poskod"><center>${line.pihak.poskod}</center></display:column>
                        <display:column title="Negeri"><center>${line.pihak.negeri.nama}</center></display:column>
                       <display:column title="Surat Alamat" ><center>
                                <c:if test="${line.pihak.suratAlamat1 != null && line.pihak.suratAlamat1 != ''}">${line.pihak.suratAlamat1},</c:if> 
                                <c:if test="${line.pihak.suratAlamat2 != null && line.pihak.suratAlamat2 != ''}">${line.pihak.suratAlamat2},</c:if> 
                                <c:if test="${line.pihak.suratAlamat3 != null && line.pihak.suratAlamat3 != ''}">${line.pihak.suratAlamat3},</c:if> 
                                <c:if test="${line.pihak.suratAlamat4 != null && line.pihak.suratAlamat4 != ''}">${line.pihak.suratAlamat4},</c:if> 
                            </center>                        
                        </display:column>
                        <display:column title="Surat Poskod"><center>${line.pihak.suratPoskod}</center></display:column>
                        <display:column title="Surat Negeri"><center>${line.pihak.suratNegeri.nama}</center></display:column>                                             
                    </display:table>                    
                </div>
            </fieldset>
        </div>
    </c:if>
</s:form>