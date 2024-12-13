<%--
   Document   : aduanLokasiPopup
   Created on : March 16, 2011, 03:24:52 PM
   Author     : latifah.iskak

--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<html><head>
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
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
        <script src="<%= request.getContextPath()%>/js/ui.datepicker-ms.js" type="text/javascript"></script>
        <script type="text/javascript">

            function save(event, f){

                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.opener.refreshPage();
                    self.close();
                },'html');

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
                if($('#bpm').val() == ''){
                    alert("Sila Pilih Bandar/Pekan/Mukim");
                    $('#bpm').focus();
                    return false;
                }

                return true;
            }
            function test(f) {
                $(f).clearForm();
            }
            
            function textCounter(field, countfield, maxlimit) {
                if (field.value.length > maxlimit) // if too long...trim it!
                    field.value = field.value.substring(0, maxlimit);
                // otherwise, update 'characters left' counter
                else
                    countfield.value = maxlimit - field.value.length;
            }
     
        </script>
        <c:if test="${actionBean.beanFlag=='kemasukan_aduan'}">
            <c:set var="beanC" value="etanah.view.penguatkuasaan.KemasukanAduanActionBean"/>
        </c:if>
        <c:if test="${actionBean.beanFlag=='senarai_aduan'}">
            <c:set var="beanC" value="etanah.view.penguatkuasaan.SenaraiAduanActionBean"/>
        </c:if>
    </head><body>
        <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
        <s:form beanclass="${beanC}">
            <s:hidden name="idPermohonan" value="actionBean.idPermohonan" />
            <s:messages/>
            <div class="instr" align="center">
                <s:errors/>
            </div>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Lokasi Kejadian
                    </legend>
                    <div class="content">

                        <p>
                            <label>Bandar/Pekan/Mukim :</label>
                            <s:select name="bandar" id="bpm">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.listBandarPekanMukim}" label="nama" value="kod" sort="nama" />
                            </s:select><em>*</em>
                            &nbsp;
                        </p>
                        <p>
                            <label>Jenis Tanah :</label>
                            <s:select name="jenisTanah" id="milik">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodPemilikan}" label="nama" value="kod" sort="nama" />
                            </s:select>
                            &nbsp;
                        </p>
                        <p>
                            <label>Nombor Lot :</label>
                            <s:text name="noLot" onkeyup="validateNumber(this,this.value);"/> &nbsp;
                        </p>
                        <p>
                            <label>Lokasi :</label>
                            <s:textarea name="lokasi" id="message" rows="5" cols="50" onkeydown="textCounter(this.form.message,this.form.remLen,100);" onkeyup="textCounter(this.form.message,this.form.remLen,100);" onkeypress="this.value=this.value.toUpperCase();"/>&nbsp;
                        </p>
                        <p align="center">
                            <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                            <s:button class="btn" value="Simpan" name="aduanLokasiSave"  onclick="if(validateForm())save(this.name,this.form);" />
                            <s:submit class="btn"  value="Tutup" name="closePopup" onclick="self.opener.refreshPage();self.close();" />
                        </p>
                        <br>
                    </div>
                </fieldset>
            </div>
        </s:form>
    </body></html>