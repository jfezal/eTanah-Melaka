<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix='fn'  uri='http://java.sun.com/jsp/jstl/functions'%>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />


<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/scripts/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/styles/datepicker.css"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<c:set var="nextStep" value="Step6"/>
<c:choose>
    <c:when test="${actionBean.urusan.kodUrusan eq 'SMB' || actionBean.urusan.kodUrusan eq 'SMK'}" >
        <c:set var="prevStep" value="Step2"/>
    </c:when>
    <c:otherwise>
        <c:set var="prevStep" value="Step3"/>
    </c:otherwise>
</c:choose>
<c:if test="${Step4 eq true}" >
    <c:set var="prevStep" value="Step4"/>
</c:if>
<c:set var="word" value="Langkah 5"/>
<c:set var="action" value="/pembangunan/permohonan"/>
<c:if test="${!empty carian}">    
    <c:set var="nextStep" value="Step3"/>
    <c:set var="prevStep" value="Step2"/>
    <c:set var="word" value="Langkah 3"/>
    <c:set var="action" value="/daftar/carian"/>
</c:if>

<p class=title>URUSAN: ${actionBean.senaraiUrusanLabel}</p>
<p class=title>Langkah 5: Fi/Pengurangan/Fi Tambahan (Jika Ada)</p>

<s:messages />
<s:errors />

<span class=instr>Masukkan maklumat yang diperlukan di bawah jika berkenaan.</span><br/>

<s:form action="${action}" id="cajTamb">
    <s:hidden name="abaiAmaran" id="abaiAmaran"/>
    <c:if test="${actionBean.urusan.kodUrusan eq 'PHPC'}">
        <s:hidden name="bilMohon" id="bilMohon" value="${actionBean.bilMohon}"/>
    </c:if>
    <c:set var="title" value="Hakmilik Terlibat"/>
    <c:if test="${perserahan}">
        <c:set var="title" value="Perserahan Terlibat"/>
    </c:if>

    <fieldset class="aras1">
        <br>
        <table align="center" width="85%">
            <tr>
                <td align="right"><b>Id Permohonan Sebelum : &nbsp;</b></td>
                <td><s:text name="permohonanSblm" value="" /></td>
            </tr>            
        </table>
                <br><br>
<%--
        <display:table name="${actionBean.senaraiTransaksiPra}" id="row1" class="tablecloth" style="width:95%;" >

            <display:column title="Bil">
                ${row1_rowNum}
            </display:column>

            <display:column title="Urusan" group="1" >
                <b>${row1.utkUrusan.namaUrusan}</b>
            </display:column>

            <display:column title="${title}" group="2">
                <c:forTokens delims="," items="${row1.senaraiHakmilik}" var="items">
                    ${items}<br/>
                </c:forTokens>
            </display:column>

            <display:column title="Transaksi" >
                ${row1.namaUrusan}
            </display:column>


            <display:column title="Kuantiti" style="width:100px;text-align:right;" >
                ${row1.kuantiti}
            </display:column>

            <display:column property="amaun" title="Fi (RM)" style="width:100px;text-align:right;" format="{0,number, 0.00}"/>           

            <display:footer>
                <tr>
                    <td>${row1_rowNum + 1}</td>
                    <td></td><td></td>
                    <td align="left"><b>(-) Pelepasan</b></td>
                    <td></td>
                    <td><div align="right">
                            <s:text name="urusan.cajPelepasan"  size="10" style="text-align:right"
                                    id="pelepasan" onblur="changeFormat2('pelepasan')" />
                        </div></td>
                </tr>
                <tr>
                    <td>${row1_rowNum + 2}</td>
                    <td></td><td></td>
                    <td align="left"><b>(-) Pengecualian</b></td>
                    <td></td>
                    <td><div align="right">
                            <s:text name="urusan.cajPengecualian"  size="10"  style="text-align:right"
                                    id="pengecualian" onblur="changeFormat2('pengecualian')"/></div></td>
                </tr>
                <tr>
                    <td>${row1_rowNum + 3}</td>
                    <td></td><td></td>
                    <td align="left"><b>(+) Notis</b></td>
                    <td></td>
                    <td><div align="right">
                            <s:text name="urusan.cajNotis" size="10" style="text-align:right"
                                    id="notis" onblur="changeFormat2('notis')"/></div></td>
                </tr>
                <tr>
                    <td>${row1_rowNum + 4}</td>
                    <td></td><td></td>
                    <td align="left"><b>(+) Fail</b></td>
                    <td></td>
                    <td><div align="right"><s:text name="urusan.cajFail" size="10"  style="text-align:right"
                            id="fail" onblur="changeFormat2('fail')"/></div></td>
                </tr>
                <tr>
                    <td>${row1_rowNum + 5}</td>
                    <td></td><td></td>
                    <td align="left"><b>(+) Lain-lain</b></td>
                    <td></td>
                    <td><div align="right"><s:text name="urusan.cajLain" size="10"  style="text-align:right"
                            id="lain2" onblur="changeFormat2('lain2')"/></div></td>
                </tr>
                <tr>
                    <td colspan="5" align="left">Jumlah Perlu Dibayar:</td>
                    <td>
                        <input name="jumCaraBayar" value="<fmt:formatNumber value="${actionBean.jumlahCaj}" pattern="0.00"/>" id="jumCaraBayar" size="12"
                               class="number" readonly="readonly" style="background:transparent;border:0 px;" /></td>
                </tr>
            </display:footer>

        </display:table>
--%>
        <p align="center">
            <s:submit name="${prevStep}" value="Kembali" class="btn" />
            <s:submit name="CancelAll" value="Batal" class="btn" />
            <s:button  name="reset" value="Isi Semula" class="btn"  onclick="clearText('cajTamb');"/>
            <s:submit name="Bayaran" value="Seterusnya" class="btn" id="submitBayaran" />
        </p>

    </fieldset>

</s:form>

<script language="javascript" type="text/javascript">
    function changeFormat2(objId){
        objId = '#' + objId;
        var val = $(objId).val();
        if (isNaN( parseFloat(val))) $(objId).val("0.00");
        else $(objId).val(parseFloat(val).toFixed(2));
        
        // update total
        var pelepasan = 0;
        if ($('#pelepasan').val() != "" && !isNaN($('#pelepasan').val())) pelepasan = -1 * parseFloat($('#pelepasan').val());
        var pengecualian = 0;
        if ($('#pengecualian').val() != "" && !isNaN($('#pengecualian').val())) pengecualian = -1 * parseFloat($('#pengecualian').val());
        var notis = 0;
        if ($('#notis').val() != "" && !isNaN($('#notis').val())) notis = parseFloat($('#notis').val());
        var fail = 0;
        if ($('#fail').val() != "" && !isNaN($('#fail').val())) fail = parseFloat($('#fail').val());
        var lain2 = 0;
        if ($('#lain2').val() != "" && !isNaN($('#lain2').val())) lain2 = parseFloat($('#lain2').val());

        var total = ${actionBean.jumlahCaj} + pelepasan +  pengecualian +
            notis +
            fail +
            lain2;
        
        $('#jumCaraBayar').val(total.toFixed(2));
    }
    
    function forceProceed(){
        $("#abaiAmaran").val('Y');
        $("#submitBayaran").click();
    }
</script>
