<%--
    Document   : maklumatstor
    Created on : Apr 7, 2011, 12:24:04 AM
    Author     : faidzal
    Modified By: Murali
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
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
    function validate(){
        var storAlamat1 = document.getElementById("storAlamat1").value;
        var storNegeri = document.getElementById("storNegeri").value;
        var storPoskod = document.getElementById("storPoskod").value;

        if ((storAlamat1 == ""))
        {
            alert('Sila masukkan alamat');
            document.getElementById("storAlamat1").focus();
            return false;
        }
        else if ((storPoskod == ""))
        {
            alert('Sila masukkan poskod');
            document.getElementById("storPoskod").focus();
            return false;
        }
        else if ((storNegeri == ""))
        {
            alert('Sila pilih negeri');
            document.getElementById("storNegeri").focus();
            return false;
        }
                   
        else{
            return true;
        }
    }
    
    function removeStor(){
        var st = document.getElementById("idStor").value;   
            var url = '${pageContext.request.contextPath}/strata/waran?deleteStor';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.strata.WaranPenahananActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Lokasi Stor</legend>
            <s:hidden name="idStor" id="idStor"/>
            <p>
                <label>Alamat :</label><s:text class="normal_text" name="storAlamat1" id="storAlamat1" readonly="${actionBean.readOnly}" size="50"/></p>
            <p>
                <label>&nbsp;</label><s:text class="normal_text" name="storAlamat2" readonly="${actionBean.readOnly}" size="50"/></p>
            <p>
                <label>&nbsp;</label><s:text class="normal_text" name="storAlamat3" readonly="${actionBean.readOnly}" size="50"/></p>
            <p>
                <label>&nbsp;</label><s:text class="normal_text" name="storAlamat4" readonly="${actionBean.readOnly}" size="50"/></p>
            <p>
                <label>Poskod :</label><s:text class="normal_text" name="storPoskod" id="storPoskod" maxlength="5" readonly="${actionBean.readOnly}" onkeyup="validateNumber(this,this.value);"/></p>
                <c:if test="${!actionBean.readOnly}">
                <p>
                    <s:label name="label"> Negeri :</s:label> <s:select name="storNegeri" id="storNegeri">
                        <s:option value="">Pilih ...</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                    </s:select>
                </p>
            </c:if>
            <c:if test="${actionBean.readOnly}">
                <p><s:label name="label"> Negeri :</s:label> <s:text readonly="${actionBean.readOnly}" name="namaNegeri" class="normal_text"/></p>
            </c:if>
            
            <c:if test="${!actionBean.readOnly}">
                <p>
                    <label>&nbsp;</label> <s:button name="saveStor" value="Simpan" class="btn" onclick="if (validate()){doSubmit(this.form,this.name,'page_div');}" />
                    <s:button name="deleteStor" value="Isi Semula" class="btn" onclick="doSubmit(this.form,this.name,'page_div');"/>
                </p>
            </c:if>

        </fieldset>
    </div>
</s:form>
