<%-- 
    Document   : maklumat_16G_16D
    Created on : Mar 3, 2010, 11:58:59 AM
    Author     : mazurahayati
--%>

<%--<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>


<script type="text/javascript">

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
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

<s:form beanclass="etanah.view.stripes.lelong.Maklumat16D16EActionBean">
    <s:messages/>
    <s:hidden name="enkuiri.idEnkuiri"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Borang 16G
            </legend>
            <div class="content">
                <p>
                    <label>Jenis Gadaian :</label>
                    <s:select name="enkuiri.tujuanGadaian" style="width:200px" >
                        <s:option value="Menjamin Wang Pokok">Menjamin Wang Pokok</s:option>
                        <s:option value="Wang Berkala">Wang Berkala</s:option>
                    </s:select>
                </p>
                <p>
                    <label> Tempoh :</label>
                    <s:text name="enkuiri.tempohGadaian"/> Bulan  &nbsp;
                </p>
            </div>
        </fieldset>
    </div>
    <br>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Borang 16D / 16E
            </legend>
            <div class="content">
                <p>
                    <label> Sebab-sebab Perlanggaran Perjanjian :</label>
                    <s:textarea name="enkuiri.sebabTunggakan" rows="5" cols="50"/>
                </p>
                <p>
                    <label> Tempoh Pelanggaran Perjanjian :</label>
                    <s:text name="enkuiri.tempohTunggakan"/> Bulan
                </p>
                <p>
                    <label>Tempoh Remedi :</label>
                    <s:text name="enkuiri.tempohRemedi"/> Bulan
                </p>
                <p>
                    <label>Jumlah Tunggakan (RM): </label>
                    <s:text id="amaun" name="enkuiri.amaunTunggakan" value="0.00" onkeyup="validateNumber(this,this.value);" onchange="calc(this.value);" formatPattern="#,##0.00"/>&nbsp;
                </p>
            </div>
        </fieldset>
    </div>

    <div class="content" align="right">
        <p>
            <s:button name="simpanEnkuiri" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
        </p>
    </div><br>

</s:form>

