<%-- 
    Document   : maklumat_notis
    Created on : Jan 18, 2010, 3:46:57 PM
    Author     : farah.shafilla
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<style type="text/css">
    label.infoJ {
        display:block;
        clear:both;
        margin-top: -2px;
        margin-left: 98px;
    }

    td.infoJ {
        display:block;
        clear:both;
        margin-top: 1px;
        margin-left: -2px;

    }
</style>
<script type="text/javascript">
    
    function test(f) {
        $(f).clearForm();
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

        
        if($("#tempoh").val()=="0" || $("#tempoh").val()=="" )
        {
            alert("Sila Masukkan Tempoh Mengosongkan Tanah");
            return false;
        }
        return true;
    }

    function limitText(limitField, limitNum) {
        if (limitField.value.length > limitNum) {
            limitField.value = limitField.value.substring(0, limitNum);
        }
    }

    function showReport(){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_notis?genReport", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
    }
    <%--function showReport(){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_notis?janaNotis", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
    }--%>
</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatNotisActionBean" name="form1">
    <s:messages />
    <s:errors />
    <s:hidden name="flag" value="NK"/> 
    <c:if test="${actionBean.errorMsg==false}">

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Notis Kosongkan Tanah
                </legend>
                <div class="content">
                    <c:if test="${edit}">
                        <p>
                            <label >Bil. Notis :</label>
                            ${actionBean.bil}&nbsp;
                        </p>
                        <p>
                            <label>Tempoh Mengosongkan Tanah:</label>
                            <c:if test="${actionBean.notisPenguatkuasaan.tempohHari eq '0'}"> 30 hari
                                <s:text name="tempohHari" value="30" id="tempoh" onkeyup="validateNumber(this,this.value);" maxlength="2"/> hari&nbsp;

                            </c:if>
                            <c:if test="${actionBean.notisPenguatkuasaan.tempohHari ne '0'}">
                                <s:text name="tempohHari" id="tempoh" onkeyup="validateNumber(this,this.value);" maxlength="2"/> hari&nbsp;

                            </c:if>
                        </p>
                        <p>
                            <label><em>*</em> Lokasi Kesalahan :</label>
                            <s:textarea name="lokasiKesalahan" id="lokasiKesalahan" cols="80" rows="5" class = "normal_text"/>&nbsp;
                            <font color="red" size="1">cth : Balakong </font>
                        </p>
                        <p>
                            <label ><em>*</em> Jenis Kesalahan :</label>
                            <s:textarea name="jenisKesalahan" id="jenisKesalahan" cols="80" rows="5" class = "normal_text" onkeydown="limitText(this,490);" onkeyup="limitText(this,490);"/>&nbsp;
                        </p>

                    </div>
                    <p align="center">

                        <s:button name="reset" value="Isi Semula" class="btn" onclick="return test(this.form);"/>
                        <s:button class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
                        <c:if test="${actionBean.kodNegeri eq '05'}">
                            <s:button name="genReport" id="report" value="Jana Notis" class="longbtn" onclick="showReport();"/>&nbsp;
                        </c:if>

                        <%--<s:button class="btn" onclick="showReport();" name="" value="Jana Notis"/>--%>
                    </p>
                </c:if>
                <c:if test="${view}">
                    <c:if test="${actionBean.kodNegeri eq '04'}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A'}">
                            <p>
                                <label >Bil. Notis :</label>
                                ${actionBean.bil}&nbsp;
                            </p>
                        </c:if>
                    </c:if>

                    <p>
                        <label>Tarikh Notis :</label>
                        <fmt:formatDate value="${actionBean.notisPenguatkuasaan.tarikhNotis}" pattern="dd/MM/yyyy" />&nbsp;

                    </p>
                    <p>
                        <label>Tempoh Mengosongkan Tanah :</label>
                        ${actionBean.notisPenguatkuasaan.tempohHari} hari&nbsp;
                    </p>
                    <p>
                        <label>Lokasi Kesalahan :</label>
                        ${actionBean.notisPenguatkuasaan.tempatHantar8}&nbsp;
                    </p>
                    <table>
                        <tr>
                            <td valign="top">
                                <p>
                                    <label class="infoJ">Jenis Kesalahan :</label>
                                </p>
                            </td>
                            <td class="infoJ"><font size="2px;">${actionBean.notisPenguatkuasaan.catatanPenerimaan}&nbsp;</font></td>
                        </tr>
                    </table>


                </c:if>
                <br>
                <br>
                <c:if test="${actionBean.kodNegeri eq '04'}">
                    <legend>
                        Sejarah Kemasukkan Notis
                    </legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.senaraiNotis}" pagesize="10" id="line" requestURI="/penguatkuasaan/maklumat_laporan_enkuiri">
                            <display:column title="Bil">${line_rowNum}</display:column>
                            <display:column title="Tarikh dan Masa Notis" sortable="true"><fmt:formatDate pattern="dd/MM/yyyy hh:mm aaa" value="${line.tarikhNotis}"/></display:column>
                            <display:column property="tempohHari" title="Tempoh Hari"/>
                            <display:column property="catatanPenerimaan" title="Jenis Kesalahan"/>
                            <display:column property="tempatHantar8" title="Lokasi Kesalahan"/>
                        </display:table>

                    </div>
                </c:if>
            </fieldset>
        </div>

    </c:if>
</s:form>
