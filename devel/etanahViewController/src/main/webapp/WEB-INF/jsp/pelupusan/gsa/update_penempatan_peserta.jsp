<%-- 
    Document   : update_penempatan_peserta
    Created on : Sep 14, 2012, 3:33:45 PM
    Author     : Navin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>

<script type="text/javascript">

    $(document).ready( function(){
        maximizeWindow();
    <c:if test="${!flag and !actionBean.forLot}">
            opener.refreshPnmptnPsrta();
            self.close();
    </c:if>
    <c:if test="${!flag and actionBean.forLot}">
            opener.refreshMklmtLot();
            self.close();
    </c:if>    
        });
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

</script>
        <s:useActionBean beanclass="etanah.view.ListUtil" var="listUtil"/>
<div class="subtitle" id="caw">
    <s:form beanclass="etanah.view.stripes.pelupusan.PenempatanPesertaActionBean">

        <div align="center">
            <s:errors/>
            <s:messages/>
        </div>
        <fieldset class="aras1">
            <s:hidden name="pnmptnPsrta.idPenempatanPeserta"/>

            <legend>
               Kemaskini Maklumat Peserta
            </legend>
            <c:if test="${!actionBean.forLot}">
            <p>
                <label><font color="red">*</font>Nama :</label>
                <s:text name="pnmptnPsrta.nama" id="nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Nombor Pengenalan :</label>
                <s:hidden name="pnmptnPsrta.noPengenalan" id="kp"/>
                <font color="black">${actionBean.pnmptnPsrta.noPengenalan}&nbsp;</font>
            </p>


            <p>
                <label>Umur :</label>
                <s:hidden name="pnmptnPsrta.umur" id="umur"/>
                <font color="black">${actionBean.pnmptnPsrta.umur}&nbsp;</font>
            </p>



             <div id="alamat">
            <p>
                <label for="alamat"><font color="red">*</font>Alamat Surat-Menyurat :</label>
                <s:text name="pnmptnPsrta.alamat1" id="Alamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pnmptnPsrta.alamat2" id="Alamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pnmptnPsrta.alamat3" id="Alamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pnmptnPsrta.alamat4" id="Alamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>


            <p>
                <label><font color="red">*</font>Poskod :</label>
                <s:text name="pnmptnPsrta.poskod" value="${actionBean.pnmptnPsrta.poskod}"id="suratPoskod" size="5" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
            </p>

            <p>
                <label for="Negeri"><font color="red">*</font>Negeri :</label>
                <s:select name="pnmptnPsrta.negeri.kod" id="majikanNegeri" style="width:244px">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
            </p>
            
            <p>
                <label><font color="red">*</font>Markah Temuduga :</label>
                <s:text name="pnmptnPsrta.markahTemuduga" value="${actionBean.pnmptnPsrta.markahTemuduga}"id="markahTemuduga" size="5" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
            </p>
            </div>

            <p>
                <label>&nbsp;</label>
                <s:submit name="updatePP" id="simpan" value="Simpan" class="btn" />
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
            </c:if>
            <c:if test="${actionBean.forLot}">
               <p>
                <label>Nama :</label>
                <s:hidden name="pnmptnPsrta.nama" id="kp"/>
                <font color="black">${actionBean.pnmptnPsrta.nama}&nbsp;</font>
            </p>
            <p>
                <label>Nombor Pengenalan :</label>
                <s:hidden name="pnmptnPsrta.noPengenalan" id="kp"/>
                <font color="black">${actionBean.pnmptnPsrta.noPengenalan}&nbsp;</font>
            </p>


            <p>
                <label>Umur :</label>
                <s:hidden name="pnmptnPsrta.umur" id="umur"/>
                <font color="black">${actionBean.pnmptnPsrta.umur}&nbsp;</font>
            </p>



             <div id="alamat">
            <p>
                <label for="alamat">Alamat Surat-Menyurat :</label>
                <s:hidden name="pnmptnPsrta.alamat1" id="alamat1"/>
                <font color="black">${actionBean.pnmptnPsrta.alamat1}&nbsp;</font>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:hidden name="pnmptnPsrta.alamat2" id="Alamat2"/>
                <font color="black">${actionBean.pnmptnPsrta.alamat2}&nbsp;</font>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:hidden name="pnmptnPsrta.alamat3" id="alamat3"/>
                <font color="black">${actionBean.pnmptnPsrta.alamat3}&nbsp;</font>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:hidden name="pnmptnPsrta.alamat4" id="alamat4"/>
                <font color="black">${actionBean.pnmptnPsrta.alamat4}&nbsp;</font>
            </p>


            <p>
                <label>Poskod :</label>
                <s:hidden name="pnmptnPsrta.poskod" id="poskod"/>
                <font color="black">${actionBean.pnmptnPsrta.poskod}&nbsp;</font>
            </p>

            <p>
                <label for="Negeri">Negeri :</label>
                <s:hidden name="pnmptnPsrta.negeri.kod" id="negeri"/>
                <s:hidden name="pnmptnPsrta.negeri.nama" id="negeri"/>
                <font color="black">${actionBean.pnmptnPsrta.negeri.nama}&nbsp;</font>
            </p>
            
            <p>
                <label>Markah Temuduga :</label>
                <s:hidden name="pnmptnPsrta.markahTemuduga" id="markahTemuduga"/>
                <font color="black">${actionBean.pnmptnPsrta.markahTemuduga}&nbsp;</font>
            </p>
            <p>
                <label>No Lot :</label>
                <s:text name="pnmptnPsrta.noLot" value="${actionBean.pnmptnPsrta.noLot}"id="noLot" size="10" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
            </p>
            </div>

            <p>
                <label>&nbsp;</label>
                <s:submit name="updateLot" id="simpan" value="Simpan" class="btn" />
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p> 
            </c:if>
        </fieldset>

    </s:form>
</div>

