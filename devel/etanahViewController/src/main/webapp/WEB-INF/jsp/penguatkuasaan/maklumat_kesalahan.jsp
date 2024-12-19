<%-- 
    Document   : maklumat_kesalahan
    Created on : Nov 19, 2013, 2:49:21 PM
    Author     : latifah.iskak
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/src/main/webapp/pub/scripts/datepicker.js"></script>
<script language="javascript" type="text/javascript"src="${pageContext.request.contextPath}/src/main/webapp/pub/js/datetimepicker.js"></script>
<script language="javascript" type="text/javascript"src="${pageContext.request.contextPath}/JavaScript/datetimepicker.js"></script>

<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:32em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
    }
    p{
        color:black;
        font-weight:normal;
        font-size:13px;
        padding-top:2px;
        padding-bottom:2px;
        line-height: 15pt ;
        text-align: justify;
    }
</style>

<script language="javascript" type="text/javascript">

    function validateForm(){
        var tempatSidang = document.getElementById('tempatSidang');
        var tajuk = document.getElementById('tajuk');
        
        if(tempatSidang.value == "" ){
            alert("Sila isikan Mahkamah terlebih dahulu");
            $('#tempatSidang').focus();
            return false;
        }
        if(tajuk.value == "" ){
            alert("Sila isikan Tajuk terlebih dahulu");
            $('#tajuk').focus();
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

    function limitText(limitField, limitNum) {
        if (limitField.value.length > limitNum) {
            limitField.value = limitField.value.substring(0, limitNum);
        }
    }

       
</script>

<s:form beanclass="etanah.view.penguatkuasaan.MaklumatKesalahanActionBean" name="form2">
    <s:messages/>
    <s:errors/>
    <%--<s:hidden name="permohonan.idPermohonan" />--%>
    <s:hidden name="permohonan.idPermohonan" />
    <c:if test="${edit}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Kesalahan</legend>
                <div class="content" align="center">

                    <p>
                        <label><em>*</em>Tempat Kesalahan :</label>
                        <s:text name="tempatSidang" id="tempatSidang" maxlength="150" size="50" class="normal_text"> </s:text>
                        </p>

                        <p>
                            <label class="center"><em>*</em>Butir-butir Kesalahan :</label>
                        <s:textarea name="tajuk" id="tajuk" cols="80" rows="15" class="normal_text"/>
                    </p>

                    <table width="50%">
                        <tr>
                            <td align="center">
                                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div')"/>
                                <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                            </td>
                        </tr>

                    </table>
                </div>
            </fieldset>


        </div>
    </c:if>

    <c:if test="${view}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Kesalahan</legend>
                <p><label>Tempat Kesalahan :</label>
                    ${actionBean.tempatSidang}&nbsp;
                </p>
                <p><label>Butir-butir Kesalahan : </label>
                    ${actionBean.tajuk}&nbsp;
                </p>

            </fieldset>
        </div>
    </c:if>

</s:form>

