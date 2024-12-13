<%-- 
    Document   : popup_tambah_kehadiran
    Created on : Oct 24, 2012, 9:36:54 AM
    Author     : latifah.iskak
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<script type="text/javascript">
    
    $(document).ready(function() {
        if($("#status").val() == "Y"){
            document.getElementById("ya").checked = true;
        }
        else if($("#status").val() == "T"){
            document.getElementById("tidak").checked = true;
        }
        
        if($("#idKehadiran").val() != ""){
            if($('#statusPB').val() == 'true'){
                document.getElementById("PBDiv").style.visibility = 'visible';
                document.getElementById("PBDiv").style.display = '';
            }
        }
    });
    function validate(){
        if($("#pilihPihak").val() == ""){
            alert("Sila pilih pihak");
            $("#pilihPihak").focus();
            return false;
        }
        if($("#nama").val() == ""){
            alert("Sila masukkan nama");
            $("#nama").focus();
            return false;
        }
        return true;
    }

    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshListKehadiran();
            self.close();
        },'html');

    }
    
    function findPihak(id){
        //        alert(id);
        if(id != "L"){
            var bil =  document.getElementById("recordCount").value;
            for (var i = 0; i < bil; i++){
                var noRujukanPihak = document.getElementById('noRujukanPihak'+i).value;
                //                alert("noRujukanPihak :"+noRujukanPihak);
                //                alert($('#pilihPihak').val());
                if(noRujukanPihak == $('#pilihPihak').val()){
                    alert("Rekod ini telah wujud. Sila pilih pihak yang lain.");
                    $('#pilihPihak').val($('').val());
                    return false;
                }
            }
            $.get('${pageContext.request.contextPath}/penguatkuasaan/maklumat_enkuiri?findPihak&id='+id,
            function(data){
                $("#selectedPihakDiv").replaceWith($('#selectedPihakDiv', $(data)));
                $('#nama').attr("readonly", true);
                $('#noPengenalan').attr("readonly", true);
                
                //                alert($('#statusPB').val());
                if($('#statusPB').val() == 'true'){
                    document.getElementById("PBDiv").style.visibility = 'visible';
                    document.getElementById("PBDiv").style.display = '';
                }
                
            }, 'html'); 
        }else{
            $('#nama').attr("readonly", false);
            $('#noPengenalan').attr("readonly", false);
        }
       

    }
    
    function validateNumber(elmnt,content) {

        //if it is fullstop (.) , then remove it..
        for( var i = 0; i < content.length; i++ )
        {
            var str = "";
            str = content.substr( i, 1 );
            if(str == "."){
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }

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
    
    function validatePoskodLength(value){
        var plength = value.length;
        if(plength != 5){
            alert('Poskod yang dimasukkan salah.');
            $('#poskod').val("");
            $('#poskod').focus();
        }
    }
    
    function jenisPengenalan(){
        if($('#kodPengenalanPb').val() == 'B'){
            document.getElementById("noPengenalanBaru").style.visibility = 'visible';
            document.getElementById("noPengenalanBaru").style.display = '';
            $('#noPengenalanLain').hide();

        }else{
            $('#noPengenalanLain').show();
            document.getElementById("noPengenalanBaru").style.visibility = 'hidden';
            document.getElementById("noPengenalanBaru").style.display = 'none';

        }
    }


</script>
<s:form beanclass="etanah.view.penguatkuasaan.EnkuiriKehadiranActionBean">
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <s:hidden name="idEnkuiri" id="idEnkuiri"/>
            <s:hidden name="idKehadiran" id="idKehadiran"/>
            <div>
                <legend>Kehadiran Enkuiri </legend>
                <c:if test="${actionBean.idKehadiran eq null}">
                    <p>
                        <label>Sila Pilih :</label>
                        <s:select name="pilihPihak" id="pilihPihak" onchange="findPihak(this.value);">
                            <s:option value=""> Sila Pilih </s:option>
                            <c:forEach items="${actionBean.senaraiKehadiran}" var="line">
                                <s:option value="${line.pihak.idPihak}">${line.pihak.nama}</s:option>
                            </c:forEach>
                            <s:option value="L">Bukan Pihak Berkepentingan</s:option>
                        </s:select>
                        &nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.idKehadiran ne null}">
                    <s:hidden name="pilihPihak" id="pilihPihak"/>
                </c:if>
                <div id="selectedPihakDiv">
                    <p>
                        <c:choose>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq '351' || actionBean.permohonan.kodUrusan.kod eq '352'}">
                                <label><em>*</em>Nama Waris:</label>
                            </c:when>
                            <c:otherwise>
                                <label><em>*</em>Nama :</label>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${actionBean.noRujukan eq 'L'}">
                                <s:text name="nama" id="nama" maxlength="250" size="30"/>&nbsp;
                            </c:when>
                            <c:otherwise>
                                <s:text name="nama" id="nama" maxlength="250" size="30" readonly="true"/>&nbsp;
                            </c:otherwise>
                        </c:choose>

                    </p>
                    <p>
                        <label>No.pengenalan :</label>
                        <s:text name="noPengenalan" id="noPengenalan" maxlength="12" onkeyup="validateNumber(this,this.value);" />&nbsp;
                    </p>
                    <p>
                        <label>Alamat :</label>
                        <s:text name="alamat1" size="50" maxlength="50"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:text name="alamat2" size="50" maxlength="50"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:text name="alamat3" size="50" maxlength="50"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:text name="alamat4" size="50" maxlength="50"/>
                    </p>
                    <p>
                        <label>Poskod :</label>
                        <s:text name="poskod" id="poskod" size="19" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);"/>

                    </p>
                    <p>
                        <label>Negeri :</label>
                        <s:select name="negeri"  style="width:139px;">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" sort="nama" />
                        </s:select>
                        <s:hidden name="statusPB" id="statusPB"/>
                    </p>

                </div>
                <div id="PBDiv" style="visibility: hidden; display: none">
                    <p>
                        <label>Nama Pihak Berkepentingan :</label>
                        <s:text name="namaPb" id="namaPb" size="42" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;
                    </p>
                    <p>
                        <label>Jenis pengenalan :</label>
                        <s:select name="kodPengenalanPb"  value=""  style="width:139px;" id="kodPengenalanPb" onchange="jenisPengenalan()">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama" />
                        </s:select>
                        &nbsp;
                    </p>
                    <p id="noPengenalanLain">
                        <label>No.Pengenalan :</label>
                        <s:text name="noPengenalanPbLain" id="noPengenalanPbLain" maxlength="20" onkeyup="this.value=this.value.toUpperCase();" />
                        &nbsp;
                    </p>
                    <p id="noPengenalanBaru" style="visibility: hidden; display: none">
                        <label>No.Pengenalan :</label>
                        <s:text name="noPengenalanPbBaru" id="noPengenalanPbBaru" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateKPLength(this.value);"/>
                        <font color="red" size="1">cth : 850510075342 </font>
                        &nbsp;
                    </p>
                </div>
                <p>
                    <label>Status Kehadiran :</label>
                    <input type="radio" name="statusKehadiran" value="Y" id="ya"/> Hadir
                    <input type="radio" name="statusKehadiran" value="T" id="tidak"/> Tidak Hadir &nbsp;
                    <input type="hidden" id="status" value="${actionBean.statusKehadiran}">
                </p>
            </div>

            <br/>
            <label>&nbsp</label>
            <s:button class="btn" name="simpanKehadiran" onclick="if(validate())save(this.name,this.form);" value="Simpan"/>
            <s:button name="tutup" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>

            <div id="senaraiPihakDivHidden" style="visibility: hidden; display: none">
                <!--<div id="senaraiPasukanDivHidden">-->
                <input name="recordCount" id="recordCount" value="${fn:length(actionBean.senaraiPihakHadir)}"/><br>
                <c:set value="0" var="i"/>
                <c:forEach items="${actionBean.senaraiPihakHadir}" var="senarai">
                    <input name="namaPihak${i}" id="namaPihak${i}" value="${senarai.nama}">
                    <input name="noKp${i}" id="noKp${i}" value="${senarai.noPengenalan}">
                    <input name="noRujukanPihak${i}" id="noRujukanPihak${i}" value="${senarai.noRujukan}">
                    <c:set var="i" value="${i+1}" />
                    <br>
                </c:forEach>
            </div>


            <c:if test="${view}">
                <fieldset class="aras1">
                    <div>
                    </div>
                </fieldset>
            </c:if>
        </fieldset>
    </div>
</s:form>
