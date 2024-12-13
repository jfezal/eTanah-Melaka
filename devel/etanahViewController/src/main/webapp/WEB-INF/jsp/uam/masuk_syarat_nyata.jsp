<%-- 
    Document   : masuk_syarat_nyata
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
        <title>Carian Kod Syarat Nyata</title>
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
                $('#tambahKodSyaratNyata').attr('disabled', 'true');
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
                    $('#tambahKodSyaratNyata').attr('disabled', 'true');
                    return false;
                }
                else {
                    if ((!$('#syaratNyataBaru').val() == '') && (!$('#kodSyaratNyataBaru').val() == '')) {
                        $('#tambahKodSyaratNyata').removeAttr("disabled");
                    }
                }

            }
            function validate_1(val) {
                if ((!$('#syaratNyataBaru').val() == '') && (!$('#kodSyaratNyataBaru').val() == '')) {
                    $('#tambahKodSyaratNyata').removeAttr("disabled");
                }
                else {
                    $('#tambahKodSyaratNyata').attr('disabled', 'true');
                }
            }
            function process() {
                var url = '${pageContext.request.contextPath}/uam/kemasukan_kod_syarat_nyata?tambahKodSyaratNyata';
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
        <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>    
            <s:form beanclass="etanah.view.uam.KemasukanKodSyaratNyata"> 
                <table class="tablecloth" width="85%" style="margin-left: auto; margin-right: auto;">
                    <tr>                        
                        <td>
                            <fieldset class="aras1">                            
                                <p>
                                    <label>Syarat Nyata :</label>
                                    <s:textarea name="syaratNyataBaru"  id="syaratNyataBaru" rows="8" cols="90" class="normal_text" onchange="validate_1(this.value);"/>
                                </p>
                                <p>
                                    <label>Kategori Tanah :</label>
                                    <s:select style="text-transform:uppercase" name="kodKatTanahBaru">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiKodKategoriTanah}" label="nama" value="kod" />
                                    </s:select>
                                </p>
                                <p>
                                    <label>Kod yang akan digunakan :</label>
                                    <s:text name="kodSyaratNyataBaru" id="kodSyaratNyataBaru" maxlength="7" size="20" readonly="false" onchange="validate(this.value);"/>&nbsp;<font size="2" color="black">7 aksara</font>
                                </p>
                                <p>                                
                                    <label>&nbsp;</label> <font size="1" color="red">PERINGATAN : Kod yang masih belum digunakan boleh disemak terlebih dahulu</font>
                                </p>
                                <br>
                                <p>                                    
                                    <label>&nbsp;</label>
                                    <s:submit name="tambahKodSyaratNyata"  id="tambahKodSyaratNyata" value="Tambah" class="btn" onclick="process()"/>&nbsp;
                                    <s:button class="btn" name="reset" value="Isi Semula" onclick="clearForm(this.form);"/>
                                </p>
                            </fieldset>

                        </td>
                    </tr>
                </table> 

                <br><br>
                <table class="tablecloth" width="85%" style="margin-left: auto; margin-right: auto;">
                    <tr onclick="showMe('SC')" onmouseover="this.style.cursor = 'pointer';
                this.style.text" class="SC"><th><span class="arrow">Semakan Kod Syarat Nyata</span></th></tr>
                    <tr id="SC">
                        <td>
                            <fieldset class="aras1">                            
                                <p>
                                    <label>Kod Syarat Nyata :</label>
                                    <s:text name="kodSyaratNyata" id="kodSyaratNyata"/>
                                </p>
                                <p>
                                    <label>Kategori Tanah :</label>
                                    <s:select style="text-transform:uppercase" name="kodKatTanah">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiKodKategoriTanah}" label="nama" value="kod" />
                                    </s:select>
                                </p>
                                <p>
                                    <label>Syarat Nyata :</label>
                                    <s:text name="syaratNyata" id="syaratNyata"/>
                                </p>
                                <p>
                                    <label>&nbsp;</label>
                                    <s:submit name="cariKodSyaratNyata" value="Cari" class="btn"/>&nbsp;
                                    <s:button class="btn" name="reset" value="Isi Semula" onclick="clearForm(this.form);"/>
                                </p>
                            </fieldset>
                            </div>
                            <br>

                            <div class="subtitle">                               

                                <fieldset class="aras1">                                    
                                    <display:table style="width:100%" class="tablecloth" name="${actionBean.listKodSyaratNyata}" excludedParams="popup" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/uam/kemasukan_kod_syarat_nyata" id="line">
                                        <display:column title="Kod" property="kodSyarat"/>
                                        <display:column title="Kategori Tanah" property="kategoriTanah.nama"/>
                                        <display:column title="Syarat" property="syarat"/>
                                    </display:table>
                                    <c:if test="${fn:length(actionBean.listKodSyaratNyata) > 0}" >
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
