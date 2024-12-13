<%-- 
    Document   : tempoh_borang_2A2B
    Created on : Aug 17, 2011, 9:46:57 AM
    Author     : latifah.iskak
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
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
            alert("Sila masukkan tempoh");
            return false;
        }
        return true;
    }
</script>

<s:form beanclass="etanah.view.penguatkuasaan.MaklumatNotisActionBean" name="form1">
    <s:messages />
    <s:errors />

    <div class="subtitle">
        <fieldset class="aras1">
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq '351'}">
                <legend>
                    Maklumat Tempoh Borang 2A
                </legend>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq '352'}">
                <legend>
                    Maklumat Tempoh Borang 2B
                </legend>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq '49'}">
                <legend>
                    Maklumat Tempoh Borang 2B
                </legend>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq '127'}">
                <c:choose>
                    <c:when test="${actionBean.tempohLanjutRemedi eq true}">
                        <legend>
                            Maklumat Tempoh Lanjut Remedi
                        </legend>
                    </c:when>
                    <c:otherwise>
                        <legend>
                            Maklumat Tempoh Borang 7A
                        </legend>
                    </c:otherwise>
                </c:choose>
            </c:if>
            <div class="content">
                <c:if test="${edit}">
                    <p>

                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq '351'}">
                            <label>Tempoh Borang 2A:</label>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq '352'}">
                            <label>Tempoh Borang 2B:</label>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq '49'}">
                            <label>Tempoh Borang 2B:</label>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq '127'}">
                            <c:choose>
                                <c:when test="${actionBean.stageId eq 'perintah_tempoh_remedi'}">
                                    <label>Tempoh Lanjut Remedi:</label>
                                </c:when>
                                <c:otherwise>
                                    <label>Tempoh Borang 7A:</label>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                        <s:text name="tempohHari" id="tempoh" onkeyup="validateNumber(this,this.value);" maxlength="3"/> hari&nbsp;

                    </p>
                </div>
                <p align="right">

                    <s:button name="reset" value="Isi Semula" class="btn" onclick="return test(this.form);"/>
                    <s:button class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
                </p>
            </c:if>
            <c:if test="${view}">
                <p>
                    <label>Tarikh Notis :</label>
                    <fmt:formatDate value="${actionBean.notisPenguatkuasaan.tarikhNotis}" pattern="dd/MM/yyyy" />&nbsp;

                </p>
                <p>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '351'}">
                        <label>Tempoh Borang 2A:</label>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '352'}">
                        <label>Tempoh Borang 2B:</label>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '49'}">
                        <label>Tempoh Borang 2B:</label>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '127'}">
                        <c:choose>
                            <c:when test="${actionBean.tempohLanjutRemedi eq true}">
                                <label>Tempoh Lanjut Remedi:</label>
                            </c:when>
                            <c:otherwise>
                                <label>Tempoh Borang 7A:</label>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    ${actionBean.notisPenguatkuasaan.tempohHari} hari&nbsp;
                </p>
            </fieldset>
        </div>
    </c:if>
</s:form>
