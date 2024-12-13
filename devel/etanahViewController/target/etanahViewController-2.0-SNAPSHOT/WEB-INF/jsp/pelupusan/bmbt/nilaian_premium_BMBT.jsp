<%-- 
    Document   : nilaian_premium
    Created on : Nov 3, 2012, 11:46:36 AM
    Author     : Afham
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<script type="text/javascript">
    function formatCurrency(num) {
        num = num.toString().replace(/\$|\,/g,'');
        if(isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num*100+0.50000000001);
        cents = num%100;
        num = Math.floor(num/100).toString();
        if(cents<10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
            num = num.substring(0,num.length-(4*i+3))+','+
            num.substring(num.length-(4*i+3));
        return (((sign)?'':'-') + num + '.' + cents);
    }
    function checkIt(evt) {
        evt = (evt) ? evt : window.event
        var charCode = (evt.which) ? evt.which : evt.keyCode
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            status = "This field accepts numbers only."
            return false
        }
        status = ""
        return true
    }

    function getHakmilikDetails(val){
        
        doBlockUI();
        var edit = $('#edit').val() ;
        var url = '${pageContext.request.contextPath}/pelupusan/laporanNilaianBMBT?searchHakmilik&idHakmilik='+val+'&edit='+edit;
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });

        //        $.get(url,
        //        function(data){
        //            $('#page_div').html(data);
        //        },'html');
    }
    
    
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.stripes.pelupusan.NilaianPremiumBMBTActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset  class="aras1" id="locate">
            <legend> 
                Senarai Hakmilik Terlibat
            </legend> 

            <p>
                <label>Hakmiilik :</label>
                <s:select name="idHakmilik" id="idmohon" onchange="getHakmilikDetails(this.value)">
                    <c:forEach items="${actionBean.hakmilikPermohonanList}" var="line">
                        <s:option value="${line.id}">${line.hakmilik.idHakmilik}(${line.hakmilik.bandarPekanMukim.daerah.nama}-${line.hakmilik.bandarPekanMukim.nama})</s:option>
                    </c:forEach> 
                </s:select>
            </p>

        </fieldset>
        <fieldset class="aras1">
            <legend>Nilaian Premium</legend>
            <br>
            <p>
                <label>Tarikh Nilaian :</label>
                <s:format formatPattern="MM/dd/yyyy" value="${actionBean.tarikhRujukan}"/>

            </p>
            <p>
                <label>Nilaian Tanah RM :</label>
                <s:format formatPattern="#,##0.00" value="${actionBean.nilai}"/>
            </p>
            <c:if test="${!actionBean.viewOnly}">
                <p>
                    <label><font color="red">*</font>Bayaran Premium :</label>
                    <s:text name="premium" formatPattern="#,##0.00" onblur="this.value=formatCurrency(this.value);" onkeypress="return checkIt(event)" id="nilai" readonly="true"/>
                </p>
                <p>
                    <label>Bayaran Premium Tambahan (jika ada) :</label>
                    <s:text name="premiumT" formatPattern="#,##0.00" onblur="this.value=formatCurrency(this.value);" onkeypress="return checkIt(event)" id="nilai" />
                </p>
                <p>
                    <label><font color="red">*</font>Hasil Tahun Pertama</label>
                    <s:text name="cukaiS" formatPattern="#,##0.00" onblur="this.value=formatCurrency(this.value);" onkeypress="return checkIt(event)" id="nilai" />
                </p>
                <p>
                    <label><font color="red">*</font>Bayaran Upah Ukur</label>
                    <s:text name="ukur" formatPattern="#,##0.00" onblur="this.value=formatCurrency(this.value);" onkeypress="return checkIt(event)" id="nilai" />
                </p>
                <br>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpan3" id="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
                </p>
            </c:if>
            <c:if test="${actionBean.viewOnly}">
                <p>
                    <label><font color="red">*</font>Bayaran Premium :</label>
                    <s:format formatPattern="#,##0.00" value="${actionBean.premium}"/>
                </p>
                <p>
                    <label>Bayaran Premium Tambahan (jika ada) :</label>
                    <s:format formatPattern="#,##0.00" value="${actionBean.premiumT}"/>
                </p>
                <p>
                    <label><font color="red">*</font>Hasil Tahun Pertama</label>
                    <s:format formatPattern="#,##0.00" value="${actionBean.cukaiS}"/>
                </p>
                <p>
                    <label><font color="red">*</font>Bayaran Upah Ukur</label>
                    <s:format formatPattern="#,##0.00" value="${actionBean.ukur}"/>
                </p>
            </c:if>

        </fieldset>
    </div>
</s:form>

