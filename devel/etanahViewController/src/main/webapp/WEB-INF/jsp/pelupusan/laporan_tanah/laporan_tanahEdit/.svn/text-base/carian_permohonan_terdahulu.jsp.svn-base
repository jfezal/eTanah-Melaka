<%-- 
    Document   : carian_permohonan_terdahulu1
    Created on : June 17, 2011, 3:23:57 PM
    Author     : Murali
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<head>
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
</head>
<script type="text/javascript">
    $(document).ready( function() {

    <%--  maximizeWindow();--%>
            var len = $(".daerah").length;

            for (var i=0; i<=len; i++){
                $('.hakmilik'+i).click( function() {
                    window.open("${pageContext.request.contextPath}/hakmilik?hakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
                });
            }
        });
        function validate()
        {
            a = document.test.id ;
  
            if (document.test.id.value==null||document.test.id.value=="")
            {

                alert("Sila masukkan id permohonan");
                a.focus()
                return false;
            }
            else
            {
     
                return true ;
            }
  
        }

        function test1(){
    
            self.close();
        }

        function save1(event, f){
          
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);                      
                // self.opener.refreshRizab();
                opener.refreshlptn();
                self.close();
                    
            },'html');
            alert("Maklumat Telah Disimpan") ;
            
        }
        $(document).ready(function() {
            $("#close").click( function(){
                setTimeout(function(){
                    self.close();
                }, 100);
            });
        });
        
        function openFrame(type){        
            var idHakmilik = $('#idHakmilik').val();
            //        alert(idHakmilik);
            NoPrompt();
            //    alert(idHakmilik);
            window.open("${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?openFrame&idHakmilik="+idHakmilik+"&type="+type, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
            //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
        }
        
        function refreshpage(){
            //        alert('aa');
            NoPrompt();
            opener.refreshV2('main');
            self.close();
        }
</script>
<body>
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true; 
        window.onbeforeunload = WarnUser;
        function WarnUser()
        {   
            if(allowPrompt)
                refreshpage();
            if(allowPrompt)
            {
                event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
            }
            else
            {
                // Reset the flag to its default value.
                allowPrompt = true;
            }
        }
        function NoPrompt()
        {
            allowPrompt = false;
        }

        function addPermohonan(){
            NoPrompt();
            var idHakmilik = $('#idHakmilik').val();
            //alert(idHakmilik);
            window.open("${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?showFormPopUpTambah&idHakmilik="+idHakmilik, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }

        function addFail(){
        <%--alert(id);--%>
                NoPrompt();
                var idHakmilik = $('#idHakmilik').val();
                var noPtSementara = $('#noPtSementara').val();
                //            alert(idHakmilik);
                window.open("${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?showFormNoFail&idHakmilik="+idHakmilik+"&noPtSementara="+noPtSementara, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
            }

            function updateMohonTerdahulu(idMohonManual,f,tName)
            {
                NoPrompt();
                window.open("${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?updateRow&idKand="+idMohonManual+"&tName="+tName, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
            }

            function deleteMohonTerdahulu(idMohonManual,f,tName)
            {
                NoPrompt();
                if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
                    var q = $(f).formSerialize();
                    $.post('${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?deleteRowPopUp&idKand='+idMohonManual+'&tName='+tName,q,
                    function(data){
                        $('#page_div').html(data);

                    }, 'html');

                    self.refreshpage2('pTanah') ;
                }
            }

            function refreshpage2(type){
                var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?refreshpage&type='+type;
                window.open(url, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
            }

    </script>
    <s:form beanclass="etanah.view.stripes.pelupusan.LaporanTanahV2PelupusanActionBean" name="test">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <s:hidden name="noPtSementara" id="noPtSementara"/>
        <div class="subtitle">
            <c:if test="${!status}">
                <fieldset class="aras1">
                    <legend>Carian Permohonan</legend>
                    <br>
                    <div class="content" align="center">
                        <%--<table class="tablecloth">
                            <tr>
                                <td>Id Permohonan:</td>
                                <td><s:text name="id" id="id" class="required"/></td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    <s:submit name="cariPermohonan" value="Cari" class="btn" onclick="NoPrompt();"/>
                                    &nbsp;<s:reset name="IsiSemula" value="Isi Semula" class="btn" onclick="NoPrompt();"/>
                                    &nbsp;<s:button name="tutup" value="Kembali" class="btn" onclick="openFrame('pTanah')"/>
                                </td>
                            </tr>
                        </table>--%>

                        <table class="tablecloth" border="0">
                            <tr>
                                <th>No Fail</th><th>Urusan</th><th>Status Keputusan</th><th>Sebab</th><th>Keputusan Oleh</th><th>Tarikh Keputusan</th><th>Nama Pemohon</th><th>Tindakan</th>
                            </tr>
                            <c:if test="${!empty actionBean.listpermohonanTanahTerdahulu}">
                                <c:set var="i" value="1" />
                                <tr>
                                    <c:forEach items="${actionBean.listpermohonanTanahTerdahulu}" var="line">
                                        <c:if test="${line.permohonanTerdahulu ne null}">
                                            <td>
                                                ${line.permohonanTerdahulu.idPermohonan}

                                            </td>
                                        </c:if>
                                        <c:if test="${line.permohonanTerdahulu eq null}">
                                            <td>
                                                ${line.noFail}

                                            </td>
                                        </c:if>
                                        <td>
                                            ${line.permohonanTerdahulu.kodUrusan.nama}

                                        </td>
                                        <td>
                                            ${line.statusKeputusan}
                                        </td>
                                        <td>
                                            <c:if test="${line.permohonanTerdahulu.sebab ne null}">
                                                ${line.permohonanTerdahulu.sebab}
                                            </c:if>
                                            <c:if test="${line.permohonanTerdahulu.sebab eq null}">
                                                -
                                            </c:if>
                                        </td>
                                        <td>
                                            ${line.keputusanOleh}
                                        </td>
                                        <td>
                                            <s:format value="${line.tarikhKeputusan}" formatPattern="dd/MM/yyyy"/>
                                        </td>
                                        <td>
                                            ${line.namaPemohon}
                                        </td>
                                        <td>
                                            <a onclick="deleteMohonTerdahulu(${line.permohonanManualSemasa.idMohonManual},this.form,'mohonManualLT')" onmouseover="this.style.cursor='pointer';" ><img alt="Hapus" height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/not_ok.gif'/></a>
                                                <c:if test="${fn:length(actionBean.pmList) > 0}">
                                                <a onclick="updateMohonTerdahulu(${line.permohonanManualSemasa.idMohonManual},this.form,'mohonManualLT')" onmouseover="this.style.cursor='pointer';" ><img alt="Kemaskini" height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                                                </c:if>
                                        </td>
                                    </tr>

                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </c:if>
                        </table>
                    </div>
                </fieldset>
                <fieldset class="aras1">
                    <table  width="100%" border="0">
                        <tr>
                            <td align="center">
                                <s:button name="Tambah" id="save" value="Cari Permohonan Terdahulu" class="longbtn" onclick="addPermohonan()"/>
                                <s:button name="TambahNoFail" id="save" value="Tambah No Fail" class="btn" onclick="addFail()"/>
                                <s:button name="tutup" value="Kembali" class="btn" onclick="openFrame('pTanah')"/>
                            </td>
                        </tr>
                    </table>
                </fieldset>
            </c:if>
            <br/>
            <%--<c:if test="${status}">
                <fieldset class="aras1">
                    <legend>Status Permohonan Terdahulu ( ${actionBean.prmhnn.kodUrusan.nama} )</legend>
                    <br>
                    <div class="content" align="center">
                        <s:hidden name="id2" value="id"/>
                        <table class="tablecloth">
                            <tr>
                                <td>Status Keputusan</td>
                                <td>:</td>
                                <td><c:if test="${actionBean.prmhnn.keputusan.nama ne null}">${actionBean.prmhnn.keputusan.nama}</c:if>
                                    <c:if test="${actionBean.prmhnn.keputusan.nama eq null}">Tiada Maklumat</c:if></td>
                            </tr>
                            <tr>
                                <td>Tarikh Keputusan</td>
                                <td>:</td>
                                <td><c:if test="${actionBean.prmhnn.tarikhKeputusan ne null}">
                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.prmhnn.tarikhKeputusan}"/>
                                    </c:if>
                                    <c:if test="${actionBean.prmhnn.tarikhKeputusan eq null}">Tiada Maklumat</c:if></td>
                            </tr>
                            <tr>
                                <td>Keputusan Oleh</td>
                                <td>:</td>
                                <td><c:if test="${actionBean.prmhnn.keputusanOleh.nama ne null}">${actionBean.prmhnn.keputusanOleh.nama}</c:if>
                                    <c:if test="${actionBean.prmhnn.keputusanOleh.nama eq null}">Tiada Maklumat</c:if></td>
                            </tr>
                            <tr>
                                <td>Tarikh Permohonan</td>
                                <td>:</td>
                                <td><c:if test="${actionBean.prmhnn.infoAudit.tarikhMasuk ne null}">
                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.prmhnn.infoAudit.tarikhMasuk}"/>
                                    </c:if>
                                    <c:if test="${actionBean.prmhnn.infoAudit.tarikhMasuk eq null}">Tiada Maklumat</c:if></td>
                            </tr>
                            <tr>
                                <td>Pemohon</td>
                                <td>:</td>
                                <td><c:if test="${actionBean.pemohon.pihak.nama ne null}">${actionBean.pemohon.pihak.nama} <br>
                                        ${actionBean.pemohon.pihak.noPengenalan} </c:if>
                                    <c:if test="${actionBean.pemohon.pihak.nama eq null}">Tiada Maklumat</c:if></td>
                            </tr>
                            <tr>
                                <td align="left" colspan="3">
                                    <c:if test="${!simpan}">
                                        <s:submit name="simpanPermohonanSblm" value="Simpan" class="btn" onclick="NoPrompt();"/>
                                        <s:button name="simpanPermohonanSblm" value="Simpan" class="btn"/>
                                    </c:if>
                                    &nbsp;<s:button name="cariSemula" value="Kembali" class="btn" onclick="openFrame('pTanah')"/>                        
                                </td>
                            </tr>
                        </table>
                    </div>            
                </fieldset>
            </c:if>--%>
        </div>
    </s:form>
</body>

