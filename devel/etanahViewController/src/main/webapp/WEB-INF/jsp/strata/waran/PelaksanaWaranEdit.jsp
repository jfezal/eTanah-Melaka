<%--
    Document   : PelaksanaWaranEdit
    Created on : Sep 14, 2011, 07:24:04 PM
    Author     : Murali
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
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
    function save(event, f){       
        var nama = document.getElementById("nama").value;
        var jenisKP = document.getElementById("jenisKP").value;
        var noPengenalan = document.getElementById("noPengenalan").value;
        var alamat1 = document.getElementById("alamat1").value;
        var poskod = document.getElementById("poskod").value;
        var negeri = document.getElementById("negeri").value;


        if ((nama == ""))
        {
            alert('Sila masukkan nama');
            document.getElementById("nama").focus();
            return false;
        }
        else if ((jenisKP == ""))
        {
            alert('Sila pilih jenis pengenalan');
            document.getElementById("jenisKP").focus();
            return false;
        }
        else if ((noPengenalan == ""))
        {
            alert('Sila masukkan no pengenalan');
            document.getElementById("noPengenalan").focus();
            return false;
        }
        else if ((alamat1 == ""))
        {
            alert('Sila masukkan alamat');
            document.getElementById("alamat1").focus();
            return false;
        }
        else if ((poskod == ""))
        {
            alert('Sila masukkan poskod');
            document.getElementById("poskod").focus();
            return false;
        }
        else if ((negeri == ""))
        {
            alert('Sila pilih negeri');
            document.getElementById("negeri").focus();
            return false;
        }          
        else{
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.get(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.close();

            },'html');
            return true;
        }
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form name="form1" beanclass="etanah.view.strata.PelaksanaWaranActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden  name="id" value="${actionBean.pelaksanaWaran.idPelaksanaWaran}"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Kemaskini Pelaksana Waran</legend>

            <p>
                <label title="Nama" >Nama :</label><s:text name="nama" id="nama" size="50"/>
            </p>
            <p>
                <label>Jenis Pengenalan :</label>
                <s:select id="jenisKP" name="jenisKP" >
                    <s:option value="">Pilih ...</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <label title="No Pengenalan" >No Pengenalan :</label><s:text id="noPengenalan" name="noPengenalan"/>
            </p>

            <p>
                <label title="Alamat">Alamat :</label><s:text id="alamat1" name="alamat1" size="50"/>
            </p>
            <p>
                <label title="Alamat">&nbsp;</label><s:text id="alamat2"  name="alamat2" size="50"/>
            </p>
            <p>
                <label title="Alamat">&nbsp;</label><s:text id="alamat3"  name="alamat3" size="50"/>
            </p>
            <p>
                <label title="Alamat">&nbsp;</label><s:text id="alamat4"  name="alamat4" size="50"/>
            </p>
            <p>
                <label title="Poskod">Poskod :</label><s:text formatType="number"  id="poskod" maxlength="5" name="poskod" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label>Negeri :</label>
                <s:select id="negeri" name="negeri" >
                    <s:option value="">Pilih ...</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <label title="Pekerjaan">Pekerjaan :</label><s:text name="kerja"/>

            </p>
            <p >
                <label>&nbsp;</label> <s:button name="upDatePelaksana" id="simpan" value="Simpan" class="btn" onclick="save(this.name,this.form)"/>
                <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
            </p>
            <br>
            <br>
        </fieldset>
    </div>
</s:form>
