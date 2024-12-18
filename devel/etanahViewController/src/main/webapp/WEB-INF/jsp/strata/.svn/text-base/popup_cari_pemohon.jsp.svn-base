<%-- 
    Document   : popup_cari_pemohon
    Created on : Jul 4, 2011, 10:24:30 AM
    Author     : zadhirul.farihim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $('.alphanumeric').alphanumeric();

    });
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.get(url,q,
        function(data){
            if(data == '1')
            {
                alert('Sila masukkan data pada label yang bertanda *. ');
            }else{$('#page_div',opener.document).html(data);

                self.close();}

        },'html');
    }

    function validation(){

        if($("#nama").val() == ""){
            alert('Sila masukkan " nama " terlebih dahulu.');
            $("#nama").focus();
            return false;
        }
      
        else if($("#jenisPengenalan").val() == ""){
            alert('Sila pilih " Jenis Pengenalan " terlebih dahulu.');
            $("#jenisPengenalan").focus();
            return false;
        }

        else if($("#nomborPengenalan").val() == ""){
            alert('Sila masukkan " Nombor Pengenalan " terlebih dahulu.');
            $("#nomborPengenalan").focus();
            return false;
        }
        
        else{
            return true;
        }

    }

    function savePemohon(event, f){
        if(validation()==true){
            
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.close();
            },'html');
        }
  
    }
    
    $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });


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
    function addNewPemohon(){
        window.open("${pageContext.request.contextPath}/strata/maklumat_pemohon?pemohonPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=750");
    }
    function resetFrom(){
        $('#namaPemohon').val('');
        $('#kodPengenalan').val('');
        $('#noPengenalan1').val('');
        $('#noPengenalan2').val('');
    }
    function pilihPihak(event, f){
        
        var q = $(f).formSerialize();
        var url;
        var len = ${fn:length(actionBean.senaraiPihak)};
            
        for(var i=1; i<=len; i++){
            if($('#pickpihak'+i).is(':checked')){
                var va = $('#pickpihak'+i).val();
                //                alert(va);
                url = "${pageContext.request.contextPath}/strata/kemasukan_pemohon?simpanPemohonPopup2&idPihak=" + va;
                
                $.get(url,q,
                function(data){
                    if (data == null || data.length == 0){
                        alert("Maklumat tidak dijumpai");
                        return;
                    }
                               
                    $('#page_div',opener.document).html(data);
                    self.close();
                },'html');
                break;
                 
            }
        }
        /**
        $('.pickpihak').each(function(){
            if($('.pickpihak').is(':checked'))
            {
                var va = $(this).val();
                alert(va);
                url = "${pageContext.request.contextPath}/strata/kemasukan_pemohon?simpanPemohonPopup2&idPihak=" + va;
                       
                            
                $.get(url,q,
                function(data){
                    if (data == null || data.length == 0){
                        alert("Maklumat tidak dijumpai");
                        return;
                    }
                                
                    $('#page_div',opener.document).html(data);
                    self.close();
                },'html');
        
            }else{
                alert("Sila pilih pemohon sebelum simpan");
                return false;
            }
        });**/
    
    }

    function noPengenalanhide(){
        if($('#kodPengenalan').val() == 'B'){
            <%--alert("selected item k/p baru:");--%>
            <%--alert("hide peng1:");--%>
            $('#noPeng1').hide();
            $('#noPengenalan1').val("");
            $('#noPeng2').show();
        }else{
            $('#noPeng1').show();
            $('#noPengenalan2').val("");
            $('#noPeng2').hide();
        }
    }

    $(document).ready(function() {
                var w = document.form1.kodPengenalan.selectedIndex;
                var st = document.form1.kodPengenalan.options[w].text;
                <%--alert("selected item:"+st);--%>
                if(st == 'No K/P Baru'){                   
                    $('#noPeng2').show();
                    $('#noPengenalan1').val("");
                    $('#noPeng1').hide();
                }else{
                    $('#noPeng1').show();
                    $('#noPengenalan2').val("");
                    $('#noPeng2').hide();
                }
                if(${actioBean.index == '1'}){
                    var noPengenalan = '${actioBean.carianNoPengenalan}';
                    $('#noPengenalan1').val(noPengenalan);
                }
                if(${actioBean.index == '2'}){
                    var noPengenalan = '${actioBean.carianNoPengenalan}';
                    $('#noPengenalan2').val(noPengenalan);
                }
            });

         function pihakcary(f){
             <%--alert("caryp");--%>
             var noPengenalan = '';
             var index = '';
             if($('#noPengenalan1').val() != ''){
                 noPengenalan = $('#noPengenalan1').val();
                 index = '1';
             }
             if($('#noPengenalan2').val() != ''){
                 noPengenalan = $('#noPengenalan2').val();
                 index = '2';
             }
              var q = $(f).formSerialize();
              <%--alert("noPeng-"+noPengenalan);--%>
              <%--alert("index-"+index);--%>
            var url = '${pageContext.request.contextPath}/strata/kemasukan_pemohon?cariPihak&noPengenalan='+noPengenalan+'&index='+index;
            <%--alert(url);--%>
            window.document.forms[0].action=url;
            window.document.forms[0].submit();
            <%--$.post(url,q,
            function(data){
                 $('#page_div').html(data);
            },'html');--%>
        }      

</script>
<style type="text/css">
    input.error { background-color: yellow; }
</style>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<div class="subtitle">
    <s:form name="form1" beanclass="etanah.view.strata.KemasukanPemohonActionBean" >
        <s:hidden name="idPihak"/>
        <s:errors/>
        <s:messages/>
        
        <fieldset class="aras1">
            <legend>Carian Maklumat Pemohon</legend>
            <p>
                <label><font color="red">*</font>Nama :</label>
                <s:text name="namaPemohon" id="namaPemohon" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>            
            <label>
                <i>atau</i>
            </label>&nbsp;

            <p>
                <label><font color="red">*</font>Jenis Pengenalan :</label>
                <s:select name="kodPengenalan" id="kodPengenalan" onchange="noPengenalanhide()">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod" />
                </s:select>
            </p>           
            <p id="noPeng1">
                <label><font color="red">*</font>No Pengenalan :</label>
                <s:text name="noPengenalan1" id="noPengenalan1" size="40"/>
            </p>
            <p id="noPeng2">
                <label><font color="red">*</font>No Pengenalan :</label>
                <s:text name="noPengenalan2" id="noPengenalan2" size="40" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
            </p>  
        </fieldset>
        <br>
        <div align="center">
            <%--<s:submit name="cariPihak" value="Cari" class="btn" onmouseover="this.style.cursor='pointer';"/>&nbsp;--%>
            <s:button name="cariPihak" value="Cari" class="btn" onmouseover="this.style.cursor='pointer';" onclick="pihakcary(this.form)"/>&nbsp;
            <s:button class="btn" value="Isi Semula" name="new" id="new" onmouseover="this.style.cursor='pointer';" onclick="resetFrom();"/>&nbsp;
            <c:if test="${!cariByIC and !cariByNama}">
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()" onmouseover="this.style.cursor='pointer';"/>
            </c:if>
        </div>
        <c:if test="${cariByNama and fn:length(actionBean.senaraiPihak)>0}">
            <fieldset class="aras1">
                <legend>Senarai Maklumat Pihak</legend>
                <p><font size="2" color="red">Sila pilih pemohon sebelum menekan butang 'Simpan'</font></p>
                <div class="content" align="center">

                    <display:table class="tablecloth" name="${actionBean.senaraiPihak}" pagesize="5" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/strata/kemasukan_pemohon?cariPihak" excludedParams="cariPihak">
                        <display:column title="Bil" style="text-align:center">${line_rowNum}</display:column>
                        <display:column title="Pilih">
                            <div align="center"><s:radio name="pickpihak" class="pickpihak" id="pickpihak${line_rowNum}" value="${line.idPihak}"/></div>
                        </display:column>
                        <display:column property="nama" title="Nama" style="width:200px;vertical-align:top;"/>
                        <display:column property="jenisPengenalan.nama" title="Jenis Pengenalan" style="width:150px;vertical-align:top;"></display:column>
                        <display:column property="noPengenalan" title="No. Pengenalan" style="width:150px;vertical-align:top;"></display:column>
                        <display:column title="Alamat">
                            <c:if test="${line.alamat1 ne null}">
                                ${line.alamat1},</c:if>
                            <c:if test="${line.alamat2 ne null}">
                                ${line.alamat2},
                            </c:if>
                            <c:if test="${line.alamat3 ne null}">
                                ${line.alamat3},
                            </c:if>
                            <c:if test="${line.alamat4 ne null}">
                                ${line.alamat4},
                            </c:if>
                            <c:if test="${line.poskod ne null}">
                                ${line.poskod}
                            </c:if>
                            <c:if test="${line.negeri ne null}">
                                ${line.negeri.nama}.
                            </c:if>
                        </display:column>
                    </display:table>
                </div>
                <p></p>
            </fieldset>
            <br>
            <div align="center">
                <s:button name="simpanPemohonPopup" id="simpan" value="Simpan" class="btn" onclick="pilihPihak(this.name, this.form);" onmouseover="this.style.cursor='pointer';"/>&nbsp;  
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()" onmouseover="this.style.cursor='pointer';"/>
            </div>
        </c:if>
        <%-- <c:if test="${cariByIC}">
             <fieldset class="aras1">
                 <legend>Maklumat Pihak</legend>
                 <p>
                     <label for="nama"><font color="red">*</font>Nama :</label>
                     <s:text name="nama" id="nama" size="40"/>
                 </p>
                 <p>
                     <label for="Jenis Pengenalan" id="jenispengalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                     <s:select name="jenisPengenalan" >
                         <s:option value="">Sila Pilih</s:option>
                         <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod" />
                     </s:select>
                 </p>
                 <p>
                     <label for="No Pengenalan"><font color="red">*</font>No Pengenalan :</label>
                     <s:text name="nomborPengenalan" id="nomborPengenalan" size="40"/>
                 </p>


                <p>
                    <label for="alamat">Alamat:</label>
                    <s:text name="alamat1" size="40" id="alamat1"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text name="alamat2" size="40" id="alamat2"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text name="alamat3" size="40" id="alamat3"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text name="alamat4" size="40" id="alamat4"/>
                <p>
                    <label>Poskod :</label>
                    <s:text name="poskod" size="40" maxlength="5" id="poskod" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label for="Negeri"><font color="red">*</font>Negeri :</label>
                    <s:select name="negeri" id="negeri">
                        <s:option value="0">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <c:if test="${actionBean.checkAlamat eq 'Yes'}">
                    <p>
                        <label>&nbsp;</label>
                        <input type="checkbox" id="checkAlamat" checked="checked" name="checkAlamat" value="Yes" onclick="copyAdd();"/>
                        <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                    </p>
                </c:if>
                <c:if test="${actionBean.checkAlamat eq 'No'}">
                    <p>
                        <label>&nbsp;</label>
                        <input type="checkbox" id="checkAlamat" name="checkAlamat" value="No" onclick="copyAdd();"/>
                        <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                    </p>
                </c:if>

                <p>
                    <label for="alamat">Alamat Surat-Menyurat:</label>
                    <s:text name="suratAlamat1" id="suratAlamat1" size="40"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text name="suratAlamat2" id="suratAlamat2" size="40"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text name="suratAlamat3" id="suratAlamat3" size="40"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text name="suratAlamat4" id="suratAlamat4" size="40"/>
                </p>

                <p>
                    <label>Poskod :</label>
                    <s:text name="suratPoskod" id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                </p>

                <p>
                    <label for="Negeri">Negeri :</label>
                    <s:select name="suratNegeri" id="suratNegeri">
                        <s:option value="0">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                    </s:select>
                </p> <br>
            </fieldset>
            <br>
            <div align="center">
                <s:button name="simpanPemohonPopup" id="simpan" value="Simpan" class="btn" onclick="savePemohon(this.name, this.form);" onmouseover="this.style.cursor='pointer';"/>&nbsp;  
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()" onmouseover="this.style.cursor='pointer';"/>
            </div>
        </c:if>--%>


        <%--new code--%>
        <c:if test="${cariByIC and fn:length(actionBean.senaraiPihak)>0}">
            <fieldset class="aras1">
                <legend>Senarai Maklumat Pihak</legend>
                <p><font size="2" color="red">Sila pilih pemohon sebelum menekan butang 'Simpan'</font></p>
                <div class="content" align="center">

                    <display:table class="tablecloth" name="${actionBean.senaraiPihak}" pagesize="5" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/strata/kemasukan_pemohon?cariPihak" excludedParams="cariPihak">
                        <display:column title="Bil" style="text-align:center">${line_rowNum}</display:column>
                        <display:column title="Pilih">
                            <div align="center"><s:radio name="pickpihak" class="pickpihak" id="pickpihak${line_rowNum}" value="${line.idPihak}"/></div>
                        </display:column>
                        <display:column property="nama" title="Nama" style="width:200px;vertical-align:top;"/>
                        <display:column property="jenisPengenalan.nama" title="Jenis Pengenalan" style="width:150px;vertical-align:top;"></display:column>
                        <display:column property="noPengenalan" title="No. Pengenalan" style="width:150px;vertical-align:top;"></display:column>
                        <display:column title="Alamat">
                            <c:if test="${line.alamat1 ne null}">
                                ${line.alamat1},</c:if>
                            <c:if test="${line.alamat2 ne null}">
                                ${line.alamat2},
                            </c:if>
                            <c:if test="${line.alamat3 ne null}">
                                ${line.alamat3},
                            </c:if>
                            <c:if test="${line.alamat4 ne null}">
                                ${line.alamat4},
                            </c:if>
                            <c:if test="${line.poskod ne null}">
                                ${line.poskod}
                            </c:if>
                            <c:if test="${line.negeri ne null}">
                                    ${line.negeri.nama}.
                            </c:if>
                        </display:column>
                    </display:table>
                </div>
                <p></p>
            </fieldset>
            <br>
            <div align="center">
                <s:button name="simpanPemohonPopup" id="simpan" value="Simpan" class="btn" onclick="pilihPihak(this.name, this.form);" onmouseover="this.style.cursor='pointer';"/>&nbsp;
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()" onmouseover="this.style.cursor='pointer';"/>
            </div>
        </c:if>
        <p>&nbsp;</p>
    </div>
</s:form>

