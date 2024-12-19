<%-- 
    Document   : BayaranBaki_flow
    Created on : Oct 7, 2010, 3:01:01 PM
    Author     : mazurahayati.yusop
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<script type="text/javascript">
    function popupReset(id){
        
        if(confirm("Anda pasti untuk kemaskini baki kepada RM 0.00?")){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            var url = "${pageContext.request.contextPath}/lelong/bayaranbakiflow?reset&idLelong="+id;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                $.unblockUI();
            },'html');
        }
    }
    
    function popupResetBaki(id){
        
        if(confirm("Anda Pasti Untuk Reset Baki Kepada Asal?")){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            var url = "${pageContext.request.contextPath}/lelong/bayaranbakiflow?resetBayaran&idLelong="+id;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                $.unblockUI();
            },'html');
        }
    }
    
    

    function terusMohon(id){
    
        if(confirm("Anda Mahu Meneruskan Hakmilik ini?")){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            var url = "${pageContext.request.contextPath}/lelong/bayaranbakiflow?terusPermohonan&idLelong="+id;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                $.unblockUI();
            },'html');
        }
    }


    function showReport(){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        var url = '${pageContext.request.contextPath}/lelong/bayaranbakiflow?genReport';
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function(xhr, ajaxOptions, thrownError) {
                alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
            },
            success : function(data) {
                if(data.indexOf('Laporan telah dijana')==0){
                    alert(data);
                    $('#folder').click();
                }else {
                    alert(data);
                    $('#urusan').click();
                }
                $.unblockUI();
            }
        });
    }

    function showReportA(){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        var url = '${pageContext.request.contextPath}/lelong/bayaranbakiflow?genReportA';
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function(xhr, ajaxOptions, thrownError) {
                alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
            },
            success : function(data) {
                if(data.indexOf('Laporan telah dijana')==0){
                    alert(data);
                    $('#folder').click();
                }else {
                    alert(data);
                    $('#urusan').click();
                }
                $.unblockUI();
            }
        });
    }
    
    function showReportB(frm,event){
        //        if(validation()){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        var url = frm.action + '?' + event;
        var queryString = $(frm).formSerialize();
        $.ajax({
            type:"POST",
            url : url,
            dataType : 'html',
            data : queryString,
            error : function(xhr, ajaxOptions, thrownError) {
                alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                $.unblockUI();
            },
            success : function(data) {
                alert(data);
                $('#folder').click();
                $.unblockUI();
            }
        });
    }
    //    }
    
    //    function showReportB(){
    //        $.blockUI({
    //            message: $('#displayBox'),
    //            css: {
    //                top:  ($(window).height() - 50) /2 + 'px',
    //                left: ($(window).width() - 50) /2 + 'px',
    //                width: '50px'
    //            }
    //        });
    //        var url = '${pageContext.request.contextPath}/lelong/bayaranbakiflow?showForm3';
    //        $.ajax({
    //            type:"GET",
    //            url : url,
    //            dataType : 'html',
    //            error : function(xhr, ajaxOptions, thrownError) {
    //                alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
    //            },
    //            success : function(data) {
    //                if(data.indexOf('Laporan telah dijana')==0){
    //                    alert(data);
    //                    $('#folder').click();
    //                }else {
    //                    alert(data);
    //                    $('#urusan').click();
    //                }
    //                $.unblockUI();
    //            }
    //        });
    //    }

    $(document).ready(function(){
        $('#cetakSP').hide();
        var sdate1 = new Date();
        var sdate6 = new Date().val();
        var vsplit1 = sdate6.split('/');
        var fulldate1 = vsplit1[1]+'/'+vsplit1[0]+'/'+vsplit1[2];
        var date6 = new Date(fulldate1);

    });
    function kire(v,num){
        var now = new Date();
        var now1 = Date.parse(now);
        var ld = v.substring(0,10);
        var date2 = ld.split("-");
        var date3 = date2[1] +"/"+date2[2]+"/"+date2[0]
        var date4 = Date.parse(date3);
        var minutes=1000*60;
        var hours=minutes*60;
        var days=hours*24;
        var years=days*365;
        var d=(now1-date4)/days;
        var hari = Math.round(d);
        if (hari < 0){
            $('#test'+num).val("Lelongan belum dijalankan");
        }else{
            $('#test'+num).val(hari);
        }
    }
</script>

<s:form beanclass="etanah.view.stripes.lelong.BayaranBakiFlowActionBean" id ="selenggara_hakmilik">
    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Jumlah Bayaran Baki
            </legend>
            <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <font color="red">*</font><em><font color="black">Jika Pembida Berjaya Telah Menjelaskan Baki Akhir Bayaran, Sila Tekan Butang Pembayaran Untuk Mengemaskini Baki Kepada RM0.00</font></em><br><br>

            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiLelongan}"
                               cellpadding="0" cellspacing="0" requestURI="/lelong/bayaranbaki"
                               id="line">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <c:if test="${actionBean.bersameke eq false}">
                        <display:column property="hakmilikPermohonan.hakmilik.idHakmilik" title="ID Hakmilik"/>
                    </c:if>
                    <c:if test="${actionBean.bersameke eq true}">
                        <display:column title="ID Hakmilik">
                            ${actionBean.idHakmilik}
                        </display:column>
                    </c:if>
                    <display:column property="hargaBidaan" title="Amaun Kena Bayar (RM)" format="{0,number, 0.00}"/>
                    <display:column title="Tarikh Lelongan Awam">
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhLelong}"/>
                    </display:column>
                    <display:column title="Tarikh Akhir Bayaran">
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhAkhirBayaran}"/>
                    </display:column>
                    <display:column  title="?/120 Hari">
                        <s:text name="test${line_rowNum}" id="test${line_rowNum}" value=""/>
                        <script type="text/javascript">kire('${line.tarikhLelong}','${line_rowNum}');</script>
                    </display:column>
                    <display:column title="Baki">
                        RM <fmt:formatNumber value="${line.baki}" pattern="#,##0.00"/>
                    </display:column>
                    <display:column title="Status">
                        ${line.baki <= 0 ? "TELAH JELAS BAKI" : "BELUM JELAS BAKI" }
                    </display:column>
                    <display:column>
                        <c:if test="${fn:length(actionBean.senaraiLelongan)>1}">
                            <c:if test="${line.baki > 0}">
                                <s:button class="btn"  onclick="popupReset('${line.idLelong}');" name="" value="Pembayaran"/>
                            </c:if>
                            <c:if test="${actionBean.hideBtn eq false}">
                                <c:if test="${line.baki <= 0}">
                                    <small><s:button class="longbtn"  onclick="terusMohon('${line.idLelong}');" name="" value="Teruskan Permohonan"/></small>
                                </c:if>
                            </c:if>
                        </c:if>
                        <c:if test="${fn:length(actionBean.senaraiLelongan)==1}">
                            <c:if test="${line.baki > 0}">
                                <s:button class="btn"  onclick="popupReset('${line.idLelong}');" name="" value="Pembayaran"/>
                            </c:if>
                            <c:if test="${line.baki <= 0}">
                                <s:button class="btn" disabled="disabled" onclick="popupReset('${line.idLelong}');" name="" value="Pembayaran"/>
                            </c:if>
                        </c:if>
                    </display:column>
                    <display:column>
                        <c:if test="${line.baki <= 0}">
                            <s:button class="btn"  onclick="popupResetBaki('${line.idLelong}');" name="" value="Reset Baki"/>
                        </c:if>
                    </display:column>

                </display:table>
            </div>
            <c:if test="${actionBean.showBtn eq false}">
                <c:if test="${actionBean.disabled ne null}">
                    <p align="center">
                        <s:button name="genReport" id="report" value="Surat Bayar Baki" class="longBtn" onclick="showReport();"/>
                    <!--</p>-->
                </c:if>
            </c:if>
            <c:if test="${actionBean.melaka eq false}">
                <c:if test="${actionBean.showPTD eq true}">
                    <!--<p align="center">-->
                        <s:button name="showForm3" id="save" value="Komisyen Jualan" class="longBtn" onclick="showReportB(this.form, this.name);"/>
                    </p>
                </c:if>
            </c:if>
            <c:if test="${actionBean.showBtn eq true}">
                <p align="center">
                    <s:button name="genReportA" id="report" value="Surat Lucut Hak" class="longBtn" onclick="showReportA();"/>
                </p>
            </c:if>


        </fieldset>
    </div>
</s:form>

