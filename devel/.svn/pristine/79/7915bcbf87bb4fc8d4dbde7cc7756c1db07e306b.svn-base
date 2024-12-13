<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="list" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
        <title>Kutipan Agensi</title>

    </head>
    <body>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-plugin-validate.js"></script>
    <style type="text/css">
        <!--
        .style1 {font-size: 21px; color: #003366; font-family: "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;}
        .errFld { border: 1px solid red; }
        -->
    </style>
    <script language="javascript">

        $(document).ready(function() {
            $('input:text').each(function() {
                $(this).focus(function() {
                    $(this).addClass('focus')
                });
                $(this).blur(function() {
                    $(this).removeClass('focus')
                });
            });
            $('select').each(function() {
                $(this).focus(function() {
                    $(this).addClass('focus')
                });
                $(this).blur(function() {
                    $(this).removeClass('focus')
                });
            });

//
//            $('#txtFile').change(function() {
//                $('#filename').text($('#txtFile').val());
//            });

            $('form').submit(function() {
                return formValidation();
            });

        });


        function reload(val, form) {
            form.action = form.action + '?reloadAgensi&kodAgensi=' + val;
            form.submit();
        }

        function doSubmit(frm, event) {
            frm.action = frm.action + '?' + event;
            frm.submit();
        }

        function doSubmit2(frm, event) {
            frm.action = frm.action + '?' + event + '&status=2';
            frm.submit();
        }

    </script>


    <stripes:messages />
    <stripes:errors />

    <stripes:form action="/hasil/kutipan_agensi">

        <fieldset class="aras1">

            <legend>Muat Naik Kutipan Agensi</legend>            
            <p>
                <label><em>*</em>Agensi :</label>
                <stripes:select name="kodAgensi" style="width:400px;" class="required">
                    <stripes:option label="Pilih Agensi..."  value="" />
                    <c:forEach items="${list.senaraiKodAgensiKutipan}" var="i" >
                        <stripes:option value="${i.kod}" >${i.kod} - ${i.nama}</stripes:option>
                    </c:forEach>
                </stripes:select>
            </p>
            <p>
                <label for="kodUrusankod"><em>*</em>Fail</label>
                <stripes:file name="txtFile" id="txtFile" class="required"/>
                <!--<span id="filename"/>-->
                <stripes:hidden name="fileName" value="${actionBean.txtFile.fileName}"/>
            </p>

            <%-- <p>
                <label>Cawangan Agensi :</label>
                <stripes:select name="kodAgensiCawangan" style="width:400px;" >
                    <stripes:option label="Pilih Cawangan Agensi..."  value="0" />
                    <c:forEach items="${actionBean.senaraiAgensiCawangan}" var="i" >
                        <stripes:option value="${i.kod}" >${i.kod} - ${i.nama}</stripes:option>
                    </c:forEach>
                </stripes:select>
            </p> --%>
            <br/>
            <p>
                <label>&nbsp;</label>
                <c:if test="${empty finish}" >
                    <stripes:submit name="check" value="Muat Naik" class="btn" />
                </c:if>
            </p>

        </fieldset>
        <br/>
        <c:if test="${!empty actionBean.senaraiTrans}">
            <fieldset class="aras1">

                <legend>Senarai Akaun Cukai Terlibat</legend>
                <p align="center">


                    <display:table name="${actionBean.senaraiTrans}" class="tablecloth" id="row2" style="width:50% ">
                        <display:column title="Bil">${row2_rowNum}</display:column>
                        <display:column title="Resit Agensi">${row2.resit_agensi}</display:column>
                        <display:column title="Tarikh Resit Agensi">
                            <c:if test="${!empty row2.trh_resit_manual}">
                                <fmt:formatDate value="${row2.trh_resit_manual}" pattern="dd/MM/yyyy"/>
                            </c:if>
                        </display:column>
                        <display:column title="Akaun Cukai" >${row2.akaunCukai}</display:column>
                        <display:column title="Amaun (RM)" style="text-align:right">
                            <fmt:formatNumber value="${row2.amaun}" pattern="#,###,###.00"/>   
                        </display:column>
                        <display:column title="Status" >
                            <c:choose>
                                <c:when test="${row2.status eq '0'}">Belum Diproses</c:when>
                                <c:when test="${row2.status eq '1'}">Berjaya Diproses</c:when>
                                <c:otherwise>Tidak Berjaya Diproses</c:otherwise>                                
                            </c:choose>
                        </display:column>
                        <display:column title="Ulasan">${row2.ulasan}</display:column>      
                        <display:footer>
                        <tr>
                            <td colspan="4" align="left">Jumlah Keseluruhan (RM):</td>
                            <td>
                                <div align="right">
                                    <fmt:formatNumber value="${actionBean.total}" pattern="#,###,###.00"/>
                                </div>

                            </td>
                        </tr>
                    </display:footer>
                </display:table>                                

            </p>
            <p>
                <label>&nbsp;</label>
                <c:if test="${empty finish}" >
                    <stripes:button name="upload" value="Posting" class="btn" onclick="doSubmit(this.form, this.name);"/>
                </c:if>
                <c:if test="${!empty err}">
                    <stripes:button name="search" value="Kemaskini" class="btn" onclick="doSubmit2(this.form, this.name);"/>
                </c:if>
            </p>
        </fieldset>
    </c:if>
</stripes:form>
<br/>
</div>
</body>
</html>