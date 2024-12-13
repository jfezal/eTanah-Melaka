<%-- 
    Document   : popup_tambah_laporan_polis
    Created on : Dec 22, 2011, 11:24:21 AM
    Author     : latifah.iskak
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
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
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function save(event, f){

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshPagePolis();
            self.close();
        },'html');

    }

    function validateForm(){

        if($('#noRujukan').val()=="")
        {
            alert('Sila masukkan no. report terlebih dahulu');
            $('#noRujukan').focus();
            return false;
        }
        if($('#datepicker').val()=="")
        {
            alert('Sila tarikh terlebih dahulu');
            $('#datepicker').focus();
            return false;
        }

        if($('#jam').val()=="")
        {
            alert('Sila isikan masa laporan terlebih dahulu');
            $('#jam').focus();
            return false;
        }
        if($('#minit').val()=="")
        {
            alert('Sila isikan masa laporan terlebih dahulu');
            $('#minit').focus();
            return false;
        }
        if($('#ampm').val()=="")
        {
            alert('Sila pilih pagi atau petang pada bahagian masa laporan');
            $('#ampm').focus();
            return false;
        }
        if($('#lokasi').val()=="")
        {
            alert('Sila isikan Lokasi Balai Polis terlebih dahulu');
            $('#lokasi').focus();
            return false;
        }

        return true;
    }

    function test(f) {
        $(f).clearForm();
    }

 

    function convertText(r,t){
        var i = r.value;
        document.getElementById(t).value=i.toUpperCase();
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


    function limitText(limitField, limitNum) {
        if (limitField.value.length > limitNum) {
            limitField.value = limitField.value.substring(0, limitNum);
        }
    }


 
</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatLaporanPolisActionBean">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Laporan Polis
            </legend>
            <div class="content">
                <p>
                    <label><em>*</em>Nombor Laporan Polis dan Balai :</label>
                    <s:text name="noRujukan" id="noRujukan" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;
                </p>
                <p>
                    <label><em>*</em>Tarikh Laporan:</label>
                    <s:text name="tarikhRujukan" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="datepicker" />&nbsp;
                    <font color="red" size="1">cth : hh / bb / tttt</font>
                </p>
                <p>
                    <label><em>*</em>Masa Laporan:</label>
                    <s:select name="jam" id="jam" style="width:50px;">
                        <s:option value=""> Jam </s:option>
                        <c:forEach var="jam" begin="1" end="12">
                            <s:option value="${jam}">${jam}</s:option>
                        </c:forEach>
                    </s:select>
                    <s:select name="minit" id="minit" style="width:70px;">
                        <s:option value=""> Minit </s:option>
                        <c:forEach var="minit" begin="00" end="59" >
                            <c:choose>
                                <c:when test="${minit > 9}"><s:option value="${minit}">${minit}</s:option></c:when>
                                <c:otherwise><s:option value="0${minit}">0${minit}</s:option></c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </s:select>
                    <s:select name="ampm" id="ampm" style="width:80px">
                        <s:option value="">Pilih</s:option>
                        <s:option value="AM">PAGI</s:option>
                        <s:option value="PM">PETANG</s:option>
                    </s:select>
                </p>

                <p>
                    <label><em>*</em>Lokasi Balai Polis :</label>
                    <s:text name="lokasi" id="lokasi" size="50" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;
                </p>
                <br/><br/>
                <p><label>&nbsp;</label>
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                    <c:if test="${actionBean.idOperasiAgensi ne null}">
                        <s:button class="btn"  name="kemaskini" onclick="if(validateForm())save(this.name,this.form);" value="Kemaskini"/>
                    </c:if>
                    <c:if test="${actionBean.idOperasiAgensi eq null}">
                        <s:button class="btn"  name="simpan" onclick="if(validateForm())save(this.name,this.form);" value="Simpan"/>
                    </c:if>
                    <s:button name="tutup" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    <s:hidden name="idOperasiAgensi"/>
                    <s:hidden name="idDokumen"/>
                    <s:hidden name="idOperasi"/>

                </p>


            </div>

        </fieldset>
    </div>

</s:form>

