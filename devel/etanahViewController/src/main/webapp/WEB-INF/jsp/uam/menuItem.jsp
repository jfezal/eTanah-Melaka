<%--
    Document   : menuItem
    Created on : June 1, 2011, 4:39:04 PM
    Author     : nurashidah.rosman
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">

    function capitalizeEachWord(element) {
        //change string to capitalize each word format..
        val = element.value;
        newVal = '';
        val = val.split(' ');
        for(var c=0; c < val.length; c++) {
            newVal += val[c].substring(0,1).toUpperCase() +
                val[c].substring(1,val[c].length) + ' ';
        }
        element.value = newVal;
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
    
    function validateForm(){

        if(document.menuForm.tajuk.value=="")
        {
            alert('Sila masukkan Tajuk.');
            $('#tajuk').focus();
            return false;
        }

        if(document.menuForm.uri.value=="")
        {
            alert('Sila masukkan URI.');
            $('#uri').focus();
            return false;
        }

        if(document.menuForm.turutan.value=="")
        {
            alert('Sila masukkan Turutan.');
            $('#turutan').focus();
            return false;
        }
        if(!document.menuForm['umum'][0].checked && !document.menuForm['umum'][1].checked){
            alert('Sila set sama ada menu ini umum atau tidak.');
            $('#umum').focus();
            return false;
        }

        if(!document.menuForm['aktif'][0].checked && !document.menuForm['aktif'][1].checked){
            alert('Sila set sama ada menu ini aktif atau tidak.');
            $('#aktif').focus();
            return false;
        }
        
        return true;
    }
    
    function test(f) {
        $(f).clearForm();
    }
       
    function terus3(frm, val) {
        var url = '${pageContext.request.contextPath}/uam/menu_capai?findIdMenu&id='+val;
        frm.action = url;
        frm.submit();
    }
    
    function terus1() {
        alert('asa');
        var url = '${pageContext.request.contextPath}/uam/menu_capai&id='+val;
        frm.action = url;
        frm.submit();
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>

<s:form beanclass="etanah.view.uam.MenuItemActionBean" name="menuForm">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Pendaftaran Antaramuka Menu Baru
            </legend>
            <p>
                <font color="red">PERINGATAN :</font>  Sila Masukkan Maklumat Berikut. <br>Bertanda <font color="red">*</font> mandatori.
            </p>
            <p>
                <label><font color="red">*</font>Menu :</label>
                    <s:text name="tajuk" onchange="capitalizeEachWord(this);" id="tajuk" maxlength="50" size="40"/>
            </p>
            <p>
                <label>Kumpulan Menu :</label>
                <s:select name="idAtas" id="idAtas">
                    <s:option value=""> Sila Pilih </s:option>
                    <c:forEach items="${actionBean.senaraiMenu}" var="line">
                        <s:option value="${line.idMenu}">${line.tajuk} - (${line.idMenu})</s:option>
                    </c:forEach>
                </s:select>
            </p>
            <p>
                <label><font color="red">*</font>URI :</label>
                <s:text name="uri" id="uri" maxlength="99" size="40"/> <font color="red">cth: /uam/menu</font>
            </p>
            <!--            <p>
                            <label>URL Ikon :</label>
            <s:text name="urlIkon" onchange="this.value=this.value.toUpperCase();" id="urlIkon" maxlength="50" size="40"/>
        </p>-->
            <p>
                <label><font color="red">*</font>Turutan Menu :</label>
                    <s:text name="turutan" onkeyup="validateNumber(this,this.value);" id="turutan" maxlength="5"/>
            </p>
            <p>
                <label><font color="red">*</font>Capaian :</label>

                <c:set var="yes" value=""/>
                <c:set var="no" value=""/>
                <c:if test="${actionBean.umum eq 'Y'}">
                    <c:set var="yes" value="checked"/>
                </c:if>
                <c:if test="${actionBean.umum eq 'T'}">
                    <c:set var="no" value="checked"/>
                </c:if>

                <input type="radio" name="umum" value='Y' ${yes}/>Umum
                <input type="radio" name="umum" value='T' ${no}/>Terhad
            </p>
            <p>
                <label><font color="red">*</font>Status :</label>

                <c:set var="yes" value=""/>
                <c:set var="no" value=""/>
                <c:if test="${actionBean.aktif eq 'Y'}">
                    <c:set var="yes" value="checked"/>
                </c:if>
                <c:if test="${actionBean.aktif eq 'T'}">
                    <c:set var="no" value="checked"/>
                </c:if>

                <input type="radio" name="aktif" value='Y' ${yes}/>Aktif
                <input type="radio" name="aktif" value='T' ${no}/>Tidak Aktif
            </p>
            <p>
                <label>&nbsp;</label>
                <c:if test="${actionBean.idMenu eq null}">
                    <s:submit name="simpan" id="simpan" value="Simpan" class="btn" onclick="return validateForm();"/>
                </c:if>
                <c:if test="${actionBean.idMenu ne null}">
                    <s:submit name="edit" id="kemaskini" value="Kemaskini" class="btn" onclick="return validateForm();"/>
                </c:if>
                <c:if test="${actionBean.menuItem ne null && actionBean.menuItem.umum eq 'T'}">
                    <s:button name="terus" id="kemaskini" value="Seterusnya" class="btn"  onclick="terus3(document.forms.menuForm, '${actionBean.idMenu}')"/>

                </c:if>
                <br>
                <%--<s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>--%>
                <s:hidden name="idMenu" value="${actionBean.idMenu}"/>
                 </p>

        </fieldset>

    </div>
</s:form>