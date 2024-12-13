<%-- 
    Document   : butir_butir_cadangan
    Created on : Nov 16, 2011, 12:07:30 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <script type="text/javascript">

            function  numeralsOnly(evt) {
                evt = (evt) ? evt : event;
                var charCode = (evt.charCode) ? evt.charCode : ((evt.keyCode) ? evt.keyCode :((evt.which) ? evt.which : 0));
                if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                    return false;
                }else{
                    return true;
                }
            }

            jQuery.fn.ForceNumericOnly = function() {
                return this.each(function()     {
                    $(this).keydown(function(e)         {
                        var key = e.charCode || e.keyCode || 0;             // allow backspace, tab, delete, arrows, numbers and keypad numbers ONLY
                        return (
                        key == 8 ||
                            key == 9 ||
                            key == 46 ||
                            (key >= 37 && key <= 40) ||
                            (key >= 48 && key <= 57) ||
                            (key >= 96 && key <= 105));
                    });
                });
            };
            jQuery('.numbersOnly').ForceNumericOnly();
        </script>

    </head>
    <body>
        <s:form  beanclass="etanah.view.stripes.pelupusan.ButirbutirCadanganActionBean">
            <s:errors/>
            <s:messages/>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend> BUTIR-BUTIR CADANGAN MELOMBONG </legend>

                    <p>
                        <label>Tujuan :</label>
                        <c:if test="${!actionBean.display}">
                            <s:textarea name="sebab" id="sebab" rows="5" cols="80"/>
                        </c:if>
                        <c:if test="${actionBean.display}">
                            ${actionBean.sebab}
                        </c:if>
                    </p>
                    <p>
                        <label>Mineral yang akan dilombong :</label>
                        <c:if test="${!actionBean.display}">
                            <s:text name="catatan" id="catatan" size="80"/>
                        </c:if>                    
                        <c:if test="${actionBean.display}">
                            ${actionBean.catatan}
                        </c:if>
                    </p>
                    <p>
                        <label>Tenaga Kerja :</label>
                        <c:if test="${!actionBean.display}">
                            <s:text name="nilaiKeputusan" size="4" id="nilaiKeputusan" class="numbersOnly"/>
                        </c:if>
                        <c:if test="${actionBean.display}">
                            ${actionBean.nilaiKeputusan}
                        </c:if>
                        &nbsp;Orang
                    </p>
                    <p>
                        <label>Sumber Kewangan:</label>
                        <c:if test="${!actionBean.display}">
                            <s:text name="ulasan" id="ulasan" size="80"  />
                        </c:if>
                        <c:if test="${actionBean.display}">
                            ${actionBean.ulasan}
                        </c:if>
                    </p>
                    <c:if test="${!actionBean.display}">
                        <p>
                            <label>&nbsp;</label>
                            <s:button name="simpanButirButir" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </p>
                    </c:if>

                </fieldset>
            </div>
        </s:form>
    </body>
</html>
