<%--
    Document   : Borang_4C
    Created on : Apr 28, 2010, 5:29:36 PM
    Author     : sitifariza.hanim
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>

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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>


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

    </script>


<s:useActionBean beanclass="etanah.view.ListUtil" var="list" />

<s:form beanclass="etanah.view.stripes.pelupusan.Borang4CActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend align="justify">Borang 4C (Seksyen 72)<br>PERMIT UNTUK MENGELUARKAN BAHAN BATUAN</legend>
            <p>
                <label>Bayaran (RM) :</label>
                <s:text name="lupusPermit.bayaran" size="20" onkeyup="validateNumber(this,this.value);" />
                <s:hidden name="lupusPermit.id"/>
            </p>
               <p>
                <label>(Ringgit) :</label>
                <s:text name="lupusPermit.bayaranPerkataan"  id="bayaran1" size="50" onkeyup="this.value=this.value.toUpperCase();"/>

            </p>
            <p>
                <label>No.Resit :</label>
                <s:text name="lupusPermit.noResit"  id="noResit" size="50" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Tarikh Dikeluarkan :</label>
                <s:text name="lupusPermit.tarikhKeluar" class="datepicker" id="tarikh" formatType="date" formatPattern="dd/MM/yyyy"/>
                <label>&nbsp;</label>
            </p>

        </fieldset>
        <fieldset class="aras1">
            <legend> Jadual</legend>
            <p>
                <label>Tarikh  Mula :</label>
                <s:text name="lupusPermit.tarikhMula" class="datepicker" id="tarikhMula" formatType="date" formatPattern="dd/MM/yyyy" />
            </p>
             <p>
                 <label>Tarikh Tamat:</label>
                <s:text name="lupusPermit.tarikhTamat" class="datepicker" id="tarikhTamat" formatType="date" formatPattern="dd/MM/yyyy"/>
            </p>
            <%--<p>
                <label>Bahan batuan yang boleh dikeluarkan :</label>
                <s:text name="jenisBahan" size="50"/>
               <s:select name="lupusPermit.jenisBahanBatu.kod" style="width:150px" id="kod_warganegara">
                      <s:option value="">Sila Pilih</s:option>
                      <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
               </s:select>
            </p><label>&nbsp;</label>--%>
            <p>
                <label>Kuantiti maksima :</label>
                <s:text name="lupusPermit.kuantitiMaksimum" size="20" maxlength="10" />
            </p>
            <p>
                <label>Unit kuantiti bahan batuan :</label>
                <s:select name="unitIsipadu.kod" value="${actionBean.lupusPermit.unitIsipadu.kod}">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodUOMByIsipadu}" label="nama" value="kod" />
                </s:select>
            </p>
             <%--<p>
                <label>Syarat kuatkuasa permit :</label>
                <s:text name="lupusPermit.syaratKuatkuasa" size="50"/>
            </p>--%>
             <p>
                <label>Syarat sah laku permit :</label>
                <s:text name="lupusPermit.tempohPermit" size="50" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
             <p>
                <label>Peruntukan tambahan :</label>
                <s:text name="lupusPermit.peruntukanTambahan" size="50" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <label>&nbsp;</label>
            <p>
                <s:reset name="" value="Isi Semula" class="btn"/>
                <s:button name="simpan4c" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </fieldset>
    </div>
</s:form>
