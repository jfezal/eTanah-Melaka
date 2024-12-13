<%-- 
    Document   : rekodKeputusanPerbincangan
    Created on : Jul 17, 2020, 1:04:29 PM
    Author     : NURBAIZURA
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>


<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">



</script>

<s:form beanclass="etanah.view.stripes.pengambilan.aduan.RekodKeputusanPerbincanganActionBean">

    <fieldset class="aras1">

        <legend>
            Keputusan Perbincangan Aduan
        </legend>

        <p>
                <label><em>*</em>Tempat</label>
                <s:text name="tempat_perbincgn" id="tempat_perbincgn" size="30" onblur="doUpperCase(this.id)" />
        </p>
        
        <p>
                <label><em>*</em>Tarikh Perbincangan</label>
                <s:text name="tarikh_perbincgn" id="tarikh_perbincgn" size="30" onblur="doUpperCase(this.id)" class="datepicker" />
        </p>
        
        <p>
                <label><em>*</em>Masa</label>
                <s:text name="masa_perbincgn" id="masa_perbincgn" size="30" onblur="doUpperCase(this.id)" />
        </p>
        



        <p>
            <br>
            <label><font color="red">*</font>Ulasan :</label>&nbsp;
            <s:textarea id = "ket_perbincgn" name="ket_perbincgn" rows="10" cols="100"/>&nbsp;
        </p>

        <p>
            <label><font color="red">*</font>Keputusan :</label>&nbsp;
            <s:radio name="status_perbincgn" value="Y"/>&nbsp;Arah Bayaran
            <s:radio name="status_perbincgn" value="T"/>&nbsp;Pertikaian
        </p>
        <center>

            <s:submit name="simpan" id="simpan" value="Simpan" class="btn"/>&nbsp;
        </center>

    </fieldset>
            
             

</s:form>



