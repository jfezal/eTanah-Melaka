<%-- 
    Document   : kutipan_agensi_update
    Created on : Jul 27, 2012, 10:48:04 AM
    Author     : fikri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE html>
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


                $('#txtFile').change(function() {
                    $('#filename').text($('#txtFile').val());
                });

                $('form').submit(function() {
                    return formValidation();
                });
                var len = $('.update').length;

                if (len > 0) {
                    $('#upload').hide();
                } else {
                    $('#kkini').hide();
                }
            });

            function doCopy(fn) {
                $('#fileName').val(fn);
            }
            
            
            function hapusFail(val) {  
                if (confirm('Adakah anda pasti untuk hapus fail ' +val+'?')) {
                  
                  var url = '${pageContext.request.contextPath}/hasil/kutipan_agensi_strata?hapusFail&Nama_Fail=' + val;
                  frm = document.form1;
                  frm.action = url;
                  frm.submit();
                }
            }          
            
            function doSubmit(frm, event) {
                frm.action = frm.action + '?' + event + '&status=2';
                frm.submit();
            }

            function doSubmit2(frm, event) {
                var param = '';
                var len = $('.update').length;
                for (var i = 0; i < len; i++) {
                    param = param + '&id=' + $('#ids_' + i).val() + '&noAkaun=' + $('#noAkauns_' + i).val();
                }

                var url = '${pageContext.request.contextPath}/hasil/kutipan_agensi_strata?' + event + param;

                $.ajax({
                    type: "POST",
                    url: url,
                    dataType: 'html',
                    error: function(xhr, ajaxOptions, thrownError) {
                        alert("error=" + xhr.responseText);
                    },
                    success: function(data) {
                        var msg;
                        if (data.substring(2).length > 0)
                            msg = data.substring(2);

                        if (data.charAt(0) == '1')
                            alert("ID atau NO AKAUN tiada [" + msg + "]");
                        else if (data.charAt(0) == '2')
                            alert("NO AKAUN tiada [" + msg + "]");
                        else if (data.charAt(0) == '3')
                            alert("Hakmilik Sambungan tiada. Hakmilik " + msg + " telah dibatalkan.");
                        else {
                            alert("Kemaskini berjaya. Sila muatnaik.");
                            $('#upload').show();
                        }
                    }
                });
            }

            function validate() {
                var data = document.getElementById('agensi');
                if (data.value == '') {
                    alert('Sila Pilih salah satu agensi.');
                    return false;
                }
                else {
                    return true;
                }
            }

        </script>


        <stripes:messages />
        <stripes:errors />
        <stripes:useActionBean beanclass="etanah.view.ListUtil" id="list" />
        <stripes:form action="/hasil/kutipan_agensi_strata" name="form1"  id="form1">

            <fieldset class="aras1">

                <legend>Kemaskini Kutipan Agensi</legend>


                <p>
                    <label><em>*</em>Agensi :</label>
                    <%--<stripes:select name="kodAgensi" style="width:400px;" class="required">--%>
                    <stripes:select name="kodAgensi" style="width:400px;" id="agensi">
                        <stripes:option label="Pilih Agensi..."  value="" />
                        <c:forEach items="${list.senaraiKodAgensiKutipan}" var="i" >
                            <stripes:option value="${i.kod}" >${i.kod} - ${i.nama}</stripes:option>
                        </c:forEach>
                    </stripes:select>
                </p>        
                <br/>
                <p>
                    <label>&nbsp;</label>
                    <stripes:submit name="searchByAgensi" value="Cari" class="btn" onclick="return validate();"/>
                    <stripes:text name="fileName" id="fileName" style="display:none;"/>
                </p>

            </fieldset>
            <br/>
            <c:if test="${!empty actionBean.senaraiFail}">
                <fieldset class="aras1">

                    <legend>Senarai Fail Yang Terlibat</legend>
                    <p align="center">
                        <display:table name="${actionBean.senaraiFail}" class="tablecloth" id="row" style="width:50% ">
                            <display:column title="Bil">${row_rowNum}</display:column>                        
                            <display:column title="Nama Fail">
                                <a href="#" onclick="doCopy('${row.namaFail}');
                doSubmit(document.forms.form1, 'search')">${row.namaFail}</a>
                            </display:column>
                            <display:column title="Jumlah Transaksi Yang Gagal" style="text-align:center">
                                ${row.gagal}
                            </display:column>
                            <display:column title="Jumlah Transaksi Yang Belum Posting" style="text-align:center">
                                ${row.belumPosting}
                            </display:column>
                            <display:column title="Hapus" style="width:5%;">  
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem${row_rowNum}' onclick="hapusFail('${row.namaFail}')" onmouseover="this.style.cursor = 'pointer';" >
                            </div>
                            </display:column>  
                        </display:table>
                    </p>                
                </fieldset>
            </c:if>     


            <c:if test="${!empty actionBean.senaraiTrans}">
                <fieldset class="aras1">

                    <legend>Senarai Akaun Cukai Terlibat</legend>
                    <p>
                        <label>Nama Fail : </label>${actionBean.fileName}                    
                    </p>
                    <p align="center">
                        <display:table name="${actionBean.senaraiTrans}" class="tablecloth" id="row2" style="width:50% ">
                            <display:column title="Bil">${row2_rowNum}</display:column>
                            <display:column title="Resit Agensi">${row2.resit_agensi}</display:column>
                            <display:column title="Tarikh Resit Agensi">
                                <c:if test="${!empty row2.trh_resit_manual}">
                                    <fmt:formatDate value="${row2.trh_resit_manual}" pattern="dd/MM/yyyy"/>
                                </c:if></display:column>
                            <display:column title="Akaun Cukai" >
                                <stripes:hidden name="ids" value="${row2.id}" id="ids_${row2_rowNum-1}"/>
                                <c:if test="${empty finish && row2.status != '0'}">
                                    <stripes:text value="${row2.akaunCukai}" name="noAkauns" id="noAkauns_${row2_rowNum-1}" class="update"
                                                  />
                                </c:if>
                                <c:if test="${!empty finish || row2.status eq '0'}">
                                    ${row2.akaunCukai}
                                </c:if>
                            </display:column>
                            <display:column title="Amaun (RM)" style="text-align:right">
                                <fmt:formatNumber value="${row2.amaun}" pattern="#,###,###.00"/>
                            </display:column>
                            <display:column title="Ulasan">
                                <c:choose>
                                    <c:when test="${row2.status eq '0' && empty row2.ulasan}">
                                        Belum Diposting
                                    </c:when>
                                    <c:otherwise>
                                        ${row2.ulasan}
                                    </c:otherwise>
                                </c:choose>

                            </display:column>      
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
                <c:if test="${empty finish}">
                    <p>
                        <label>&nbsp;</label>
                        <stripes:button name="update" value="Kemaskini" class="btn" onclick="doSubmit2(this.form, this.name);" id="kkini"/>
                        <stripes:button name="upload" value="Posting" class="btn" onclick="doSubmit(this.form, this.name);" id="upload"/>                    
                    </p>
                </c:if>
            </fieldset>
        </c:if>      
    </stripes:form>
    <br/>
</div>
</body>
</html>
