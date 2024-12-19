<%-- 
    Document   : viewMohonPguna
    Created on : Sep 16, 2011, 4:57:52 PM
    Author     : Aziz
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }
    function validateForm(f) {

        
        if (( f.sokongan[0].checked == false ) && ( f.sokongan[1].checked == false)){ 
            alert ( "Sila pilih Sokongan." ); 
            return false; 
        }
        if((f.sokongan[1].checked == true)){
            if(f.elements['catatan'].value == '') {
                alert('Sila masukkan Ulasan.');
                f.elements['catatan'].focus();
                return false;
            }
        }
        if((f.sokongan[0].checked == true)){
            if(f.elements['peranankod'].value == '') {
                alert('Sila masukkan Peranan.');
                f.elements['peranankod'].focus();
                return false;
            }
        }
        else return true;
    }
    
    function hideShowUlasan(){
        if(f.elements['catatan'].value == '') {
            $('#tidak').show();
        }else{}
        
    }
    
    var browserType;

    if (document.layers) {browserType = "nn4"}
    if (document.all) {browserType = "ie"}
    if (window.navigator.userAgent.toLowerCase().match("gecko")) {
        browserType= "gecko"
    }
    
    function hide() {
        if (browserType == "gecko" ){
            document.poppedLayer = eval('document.getElementById("hideshow")');
            document.poppedLayer.style.visibility = "hidden";
            document.poppedLayer = eval('document.getElementById("hideshow1")');
            document.poppedLayer.style.visibility = "visible";
        }
        else if (browserType == "ie"){
            document.poppedLayer = eval('document.getElementById("hideshow")');
            document.poppedLayer.style.visibility = "hidden";
            document.poppedLayer = eval('document.getElementById("hideshow1")');
            document.poppedLayer.style.visibility = "visible";
        }else
            document.poppedLayer =
            document.poppedLayer = eval('document.getElementById("hideshow")');
        document.poppedLayer.style.visibility = "hidden";
        document.poppedLayer = eval('document.getElementById("hideshow1")');
        document.poppedLayer.style.visibility = "visible";
    }


    function show() {
        if (browserType == "gecko" ){
            document.poppedLayer = eval('document.getElementById("hideshow")');
            document.poppedLayer.style.visibility = "visible";
            document.poppedLayer = eval('document.getElementById("hideshow1")');
            document.poppedLayer.style.visibility = "hidden";
        }else if (browserType == "ie"){
            document.poppedLayer = eval('document.getElementById("hideshow")');
            document.poppedLayer.style.visibility = "visible";
            document.poppedLayer = eval('document.getElementById("hideshow1")');
            document.poppedLayer.style.visibility = "hidden";
        }else
            document.poppedLayer = eval('document.getElementById("hideshow")');
        document.poppedLayer.style.visibility = "visible";
        document.poppedLayer = eval('document.getElementById("hideshow1")');
        document.poppedLayer.style.visibility = "hidden";
    }
    
</script>
<div id="all">
    <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
    <s:form beanclass="etanah.view.uam.ViewMohonPgunaData" name ="viewMohonPgunaDetails" id ="viewMohonPgunaDetails">
        <s:messages/>
        <s:errors/>
        <div class="subtitle displaytag">

            <fieldset class="aras1">
                <legend>Butiran Permohonan Pengguna</legend>

                <p><label>ID Pengguna :</label>&nbsp;${actionBean.mohonPguna.idPengguna}</p>
                <p><label>Nama :</label>&nbsp;${actionBean.mohonPguna.nama}</p>
                <p><label>No Kp :</label>&nbsp;${actionBean.mohonPguna.noPengenalan}</p>
                <p><label>Alamat :</label>&nbsp;${actionBean.alamat}</p>
                <c:if test="${actionBean.alamat2 ne null}">
                    <p><label>&nbsp;</label>&nbsp;${actionBean.alamat2}</p>
                </c:if>
                <c:if test="${actionBean.alamat3 ne null}">
                    <p><label>&nbsp;</label>&nbsp;${actionBean.alamat3}</p>
                </c:if>
                <c:if test="${actionBean.alamat4 ne null}">
                    <p><label>&nbsp;</label>&nbsp;${actionBean.alamat4}</p>
                </c:if>
                <p><label>Poskod</label>&nbsp;${actionBean.poskod}</p>
                <p><label>Negeri</label>&nbsp;${actionBean.negeri}</p>
                <p><label>Jawatan Permohonan :</label>&nbsp;${actionBean.mohonPguna.jawatan}</p>
                <p><c:if test="${!actionBean.melaka}">
                        <label>Cawangan :</label></c:if>
                    <c:if test="${actionBean.melaka}">
                        <label>Jabatan :</label></c:if>&nbsp;${actionBean.mohonPguna.kodCawangan.name}</p>  
                <p>                    <c:if test="${!actionBean.melaka}">
                        <label>Jabatan :</label></c:if>
                    <c:if test="${actionBean.melaka}">
                        <label>Unit :</label></c:if>&nbsp;${actionBean.mohonPguna.kodJabatan.nama}</p>
                <p><label>Kod Status :</label>&nbsp;${actionBean.mohonPguna.status.kod}&nbsp;(${actionBean.mohonPguna.status.nama})</p>                                 

                <p><label>No Telefon :</label>&nbsp;${actionBean.mohonPguna.noTelefon}</p>                  
                <p><label>No Telefon Bimbit :</label> &nbsp;${actionBean.mohonPguna.noTelefonBimbit}</p>
                <p>
            </fieldset>
        </div>
        <c:if test="${!actionBean.stageAdmin}">

            <div class="subtitle displaytag">

                <fieldset class="aras1">
                    <s:hidden name="idPengguna"/>
                    <legend>Tindakan Penyelia</legend>
                    <p><label><font color="red">*</font>Sokongan :</label>&nbsp;
                        <s:radio name="sokongan"  value="Y" onclick="hide()">Sokong</s:radio>Sokong &nbsp;
                        <s:radio name="sokongan"  value="T" onclick="show()">Tidak diSokong</s:radio> Tidak diSokong
                        </p>
                        <div id="hideshow1" style="visibility: hidden">
                            <p>
                                <label>Peranan Utama :</label>
                            <s:select name="peranankod" style="width:250px">
                                <s:option value="" >Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.listkodPeranan}" value="kod" label="nama" id="peranan"/>
                            </s:select>
                        </p>
<c:if test="${actionBean.kaunter}">
                        <p><label>No Kaunter :</label>&nbsp;<s:text name="noKaunter" onkeyup="validateNumber(this,this.value);" maxlength="2"/></p>
                        <p><label>Pelulus Bayar Kurang :</label>&nbsp;
                            <s:radio name="pelulusBayar"  value="Y"> Ya &nbsp;&nbsp;</s:radio> Ya &nbsp;
                            <s:radio name="pelulusBayar"  value="T"> Tidak</s:radio> Tidak
                        </p>
                    </c:if>
                    </div>
                          
                    <!--                    <p>
                                            <label><font color="red">*</font>Kod Klasifikasi :</label>
                    <s:select name="klasifikasiKod" style="width:250px">
                        <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodKlasifikasi}" value="kod" label="nama" id="peranan"/>
                    </s:select>
                </p>-->


                    <div id="hideshow" style="visibility: hidden">
                        <p> 
                            <label>Ulasan :</label>
                            <s:textarea name="catatan" cols="50" class="normal_text"></s:textarea>
                            </p>
                        </div>

                        <p align="center"><s:button class="btn" name="back" value="Kembali" onclick="history.go(-1)"/>&nbsp;
                        <s:submit class="btn" name="sah" value="Hantar" onclick="return validateForm(this.form)"/>&nbsp;
                        <%--<s:submit class="btn" name="tolak" value="Tolak" onclick="return validateUlasan(this.form)"/>--%>
                    </p>
                </fieldset>
            </div>
        </c:if>
        <c:if test="${actionBean.stageAdmin}">
            <div class="subtitle displaytag">

                <fieldset class="aras1">
                    <s:hidden name="idPengguna"/>
                    <legend>Tindakan Penyelia</legend>
                    <p><label>Nama Penyelia :</label>&nbsp;${actionBean.penyelia.nama}</p>
                    <p><label>Jawatan:</label>&nbsp;${actionBean.penyelia.jawatan}</p>
                    <p><c:if test="${!actionBean.melaka}">
                            <label>Jabatan :</label></c:if>
                        <c:if test="${actionBean.melaka}">
                            <label>Unit :</label></c:if>&nbsp;${actionBean.penyelia.kodJabatan.nama}</p>
                    <p><label>Peranan Utama Pemohon:</label>&nbsp;${actionBean.perananUtama}</p>
                    <c:if test="${actionBean.kaunter}">  <p><label>Pelulus Bayar Kurang</label>&nbsp;${actionBean.pelulusbayarValue}
                        </c:if>

                        <br>
                </fieldset>
            </div>

            <div class="subtitle displaytag">

                <fieldset class="aras1">
                    <s:hidden name="idPengguna"/>
                    <legend>Tindakan Pentadbir Sistem</legend>
                   
                        <p>
                            <label>Penyelia :</label>&nbsp;


                            <input type="radio" name="penyeliaFlag" value='Y'/>&nbsp;Ya&nbsp;&nbsp;
                            <input type="radio" name="penyeliaFlag" value='T'/>&nbsp;Tidak
                        </p>


                        <br>
                    </fieldset>
                </div>
  
        <br>
        <p align="center"><s:button class="btn" name="back" value="Kembali" onclick="history.go(-1)"/>
            &nbsp;
            <s:submit class="btn" name="sendPassword" value="Sah"/>
            &nbsp;
        </p>
    </c:if>
</s:form>
</div>
