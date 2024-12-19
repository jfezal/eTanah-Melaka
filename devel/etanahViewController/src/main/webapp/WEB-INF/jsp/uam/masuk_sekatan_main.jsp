<%-- 
    Document   : carian_kodsekatan
    Created on : Dec 10, 2009, 5:26:20 PM
    Author     : zairul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carian Kod Sekatan</title>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
        <style type="text/css">
            td.s{
                font-weight:bold;
                color:blue;
                text-align: right;
            }

            .arrow { background:transparent url(${pageContext.request.contextPath}/pub/images/arrows.png) no-repeat scroll 0px -16px; width:100%; height:16px; display:block;}
            .up { background-position:0px 0px;}
        </style>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#tambahKodSekatan').attr('disabled', 'true');
            });
            function clearForm(f) {
                $(f).clearForm();
            }
            function showMe(thID) {
                $('#' + thID).toggle();
                $('.' + thID).find(".arrow").toggleClass("up");
            }
            function validate(val) {
                if (!/^[0-9]{7}$/.test(val)) {
                    alert("Pastikan Kod yang akan digunakan 7 aksara tanpa abjad");
                    $('#tambahKodSekatan').attr('disabled', 'true');
                    return false;
                }
                else {
                    if ((!$('#sekatanBaru').val() == '') && (!$('#kodSekatanBaru').val() == '')) {
                        $('#tambahKodSekatan').removeAttr("disabled");
                    }
                }

            }
            function validate_1(val) {
                if ((!$('#sekatanBaru').val() == '') && (!$('#kodSekatanBaru').val() == '')) {
                    $('#tambahKodSekatan').removeAttr("disabled");
                }
                else {
                    $('#tambahKodSekatan').attr('disabled', 'true');
                }
            }
            function process() {
                var url = '${pageContext.request.contextPath}/uam/kemasukan_kod_sekatan?tambahKodSekatan';
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top: ($(window).height() - 50) / 2 + 'px',
                        left: ($(window).width() - 50) / 2 + 'px',
                        width: '50px'
                    }
                });
                $.post(url,
                        function(data) {
                            $.unblockUI();
                        }, 'html');
            }

        </script>
    </head>
    <body>
        <s:errors/>
        <s:messages/>
        <div class="subtitle">
            <s:form beanclass="etanah.view.uam.KemasukanKodSekatan"> 
                <table class="tablecloth" width="85%" style="margin-left: auto; margin-right: auto;">
                    <tr>                        
                        <td>
                            <fieldset class="aras1">                            
                                <p>
                                    <label>Sekatan :</label>
                                    <s:textarea name="sekatanBaru"  id="sekatanBaru" rows="8" cols="90" class="normal_text" onchange="validate_1(this.value);"/>
                                </p>
                                <p>
                                    <label>Kod yang akan digunakan :</label>
                                    <s:text name="kodSekatanBaru" id="kodSekatanBaru" maxlength="7" size="20" readonly="false" onchange="validate(this.value);"/>&nbsp;<font size="2" color="black">7 aksara</font>
                                </p>
                                <p>                                
                                    <label>&nbsp;</label> <font size="1" color="red">PERINGATAN : Kod yang masih belum digunakan boleh disemak terlebih dahulu</font>
                                </p>
                                <br>
                                <p>                                    
                                    <label>&nbsp;</label>
                                    <s:submit name="tambahKodSekatan"  id="tambahKodSekatan" value="Tambah" class="btn" onclick="process()"/>&nbsp;
                                    <s:button class="btn" name="reset" value="Isi Semula" onclick="clearForm(this.form);"/>
                                </p>
                            </fieldset>

                        </td>
                    </tr>
                </table> 

                <br><br>
                <table class="tablecloth" width="85%" style="margin-left: auto; margin-right: auto;">
                    <tr onclick="showMe('SC')" onmouseover="this.style.cursor = 'pointer';
                this.style.text" class="SC"><th><span class="arrow">Semakan Kod Sekatan</span></th></tr>
                    <tr id="SC">
                        <td>
                            <fieldset class="aras1">                            
                                <p>
                                    <label>Kod Sekatan :</label>
                                    <s:text name="kodSekatan" id="kodSekatan"/>
                                </p>
                                <p>
                                    <label>Sekatan :</label>
                                    <s:text name="sekatan" id="sekatan"/>
                                </p>
                                <p>
                                    <label>&nbsp;</label>
                                    <s:submit name="cariKodSekatan" value="Cari" class="btn"/>&nbsp;
                                    <s:button class="btn" name="reset" value="Isi Semula" onclick="clearForm(this.form);"/>
                                </p>
                            </fieldset>
                            </div>
                            <br>

                            <div class="subtitle">                               

                                <fieldset class="aras1">                                    
                                    <display:table style="width:100%" class="tablecloth" name="${actionBean.listKodSekatan}" excludedParams="popup" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/uam/kemasukan_kod_sekatan" id="line">
                                        <display:column title="kod" property="kodSekatan" />
                                        <display:column title="Sekatan" property="sekatan" />
                                    </display:table>
                                    <c:if test="${fn:length(actionBean.listKodSekatan) > 0}" >
                                        <p><label>&nbsp;</label>                                                       
                                        </p>
                                    </c:if>
                                </fieldset>
                            </div>
                        </td>
                    </tr>
                </table>
            </s:form>
    </body>
</html>
