<%--
    Document   : senaraiUrusan
    Created on : Dec 23, 2009, 3:19:12 PM
    Author     : wan.fairul
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<style type="text/css">
    td.s{
        font-weight:bold;
        color:blue;
        text-align: right;
    }

    .arrow { background:transparent url(${pageContext.request.contextPath}/pub/images/arrows.png) no-repeat scroll 0px -16px; width:100%; height:16px; display:block;}
    .up { background-position:0px 0px;}
</style>
<script language="javascript">

    $(document).ready(function() {

        $('#SC').toggle();
        $('#B').toggle();
        $('#N').toggle();



    <%--set focus--%>
            $('input').focus(function() {
                $(this).addClass("focus");
            });

            $('input').blur(function() {
                $(this).removeClass("focus");
            });

            $('select').focus(function() {
                $(this).addClass("focus");
            });

            $('select').blur(function() {
                $(this).removeClass("focus");
            });
        });


        function viewUrusan(id) {
            window.open("${pageContext.request.contextPath}/daftar/pembetulan/betul?viewUrusan&idUrusan=" + id, "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
        }
        function viewUrusanBatal(id) {
            window.open("${pageContext.request.contextPath}/daftar/pembetulan/betul?viewUrusanBatal&idUrusan=" + id, "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
        }

        function addUrusan(id) {
            window.open("${pageContext.request.contextPath}/daftar/pembetulan/betul?addUrusan&idPermohonan=" + id, "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
        }

    <%--    function updateUrusan(id){
            window.open("${pageContext.request.contextPath}/daftar/pembetulan/betul?updateUrusan&idPembetulan="+id, "eTanah",
           "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");s
        }--%>

            function popup(url)
            {
                params = 'width=' + screen.width;
                params += ', height=' + screen.height;
                params += ', top=0, left=0'
                params += ', fullscreen=yes';
                params += ', directories=no';
                params += ', location=no';
                params += ', menubar=no';
                params += ', resizable=no';
                params += ', scrollbars=yes';
                params += ', status=no';
                params += ', toolbar=no';
                newwin = window.open(url, 'PopUp', params);
                if (window.focus) {
                    newwin.focus()
                }
                return false;
            }

            function showMe(thID) {

                $('#' + thID).toggle();
                $('.' + thID).find(".arrow").toggleClass("up");
            }

            function removeChanges(val, val2) {
                var answer = confirm("adakah anda pasti untuk Hapus?");

                if (answer) {
                    doBlockUI();
                    var url = '${pageContext.request.contextPath}/daftar/pembetulan/betul?deleteChanges3&idPembetulan=' + val + '&idHakmilik=' + val2;
                    $.get(url,
                            function(data) {
                                $('#page_div').html(data);
                                doUnBlockUI();
                            });
                }
            }

            function removeChanges2(val) {
                var answer = confirm("adakah anda pasti untuk Hapus Pembetulan?");
                if (answer) {
                    var url = '${pageContext.request.contextPath}/daftar/pembetulan/betul?deleteChanges4&idUrusan=' + val;
                    $.get(url,
                            function(data) {
                                $('#page_div').html(data);
                            });
                }
            }
            function removeTotal(val) {
                var answer = confirm("adakah anda pasti untuk Hapus Urusan Ini?");
                if (answer) {
                    var url = '${pageContext.request.contextPath}/daftar/pembetulan/betul?deleteTotal&idUrusan=' + val;
                    $.get(url,
                            function(data) {
                                $('#page_div').html(data);
                            });
                }
            }
</script>

<div class="subtitle displaytag">
    <s:form beanclass="etanah.view.stripes.nota.pembetulanActionBean">

        <s:messages />
        <s:errors />

        <fieldset class="aras1">
            <legend>
                Baiki Maklumat Urusan
            </legend>
            <br/>
            <div class="subtitle" align="center">
                <table class="tablecloth" width="80%" style="margin-left: auto; margin-right: auto;">
                    <tr onclick="showMe('SC')" onmouseover="this.style.cursor = 'pointer';
                this.style.text" class="SC"><th><span class="arrow">Senarai Urusan Surat Cara (SC)</span></th></tr>
                    <tr id="SC">
                        <td>
                            <fieldset class="aras1">
                                <legend>
                                </legend>
                                <p style="color:red">
                                    *Klik butang (<img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif'/>) untuk membuat pembetulan.<br/>
                                </p>
                                <div class="content" align="center">
                                    <p style="color:blue">
                                        <b>Urusan Berkuatkuasa </b><br/>
                                    </p>
                                    <display:table class="tablecloth" name="${actionBean.hakmilikUrusanSC}"  cellpadding="0" cellspacing="0" id="line"
                                                   requestURI="${pageContext.request.contextPath}/daftar/pembetulan/betul" pagesize="10">
                                        <display:column title="Bil">${line_rowNum}</display:column>
                                        <display:column property="idPerserahan" title="ID Perserahan" sortable="true"/>
                                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" sortable="true"/>
                                        <display:column property="kodUrusan.kod" title="Kod Urusan" sortable="true"/>
                                        <display:column property="kodUrusan.nama" title="Urusan" sortable="true"/>

                                        <display:column title="Baiki">
                                            <div align="center">
                                                <p align="center">
                                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                         onclick="viewUrusan('${line.idUrusan}');
                return false;" onmouseover="this.style.cursor = 'pointer';">
                                                </p>
                                            </div>
                                        </display:column>
                                        <display:column title="Baiki Urusan Terperinci">
                                            <p align="center">

                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                     onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?updateUrusanTerperinci&idUrusan=${line.idUrusan}&idHakmilik=${line.hakmilik.idHakmilik}');"  onmouseover="this.style.cursor = 'pointer';">
                                            </p>
                                        </display:column>
                                        <display:column title="Hapus Pembetulan">
                                            <div align="center">
                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id='rem${line_rowNum}' onclick="removeChanges2('${line.idUrusan}')" onmouseover="this.style.cursor = 'pointer';" >
                                            </div>
                                        </display:column>
                                            
                                        <%--display:column title="Hapus Urusan">  #23012020 comment by azizi sbb user suka2 delete urusan. Uncomment bila perlu.
                                            <div align="center">
                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id='rem${line_rowNum}' onclick="removeTotal('${line.idUrusan}')" onmouseover="this.style.cursor = 'pointer';" >
                                            </div>
                                        </display:column--%>
                                        
                                    </display:table>

                                    <p style="color:blue">
                                        <br/><b>Urusan Tidak Berkuatkuasa </b><br/>
                                    </p>
                                    <display:table class="tablecloth" name="${actionBean.hakmilikUrusanSCbatal}"  cellpadding="0" cellspacing="0" id="line"
                                                   requestURI="${pageContext.request.contextPath}/daftar/pembetulan/betul" pagesize="10">
                                        <display:column title="Bil">${line_rowNum}</display:column>
                                        <display:column property="idPerserahan" title="ID Perserahan" sortable="true"/>
                                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" sortable="true"/>
                                        <display:column property="kodUrusan.kod" title="Kod Urusan" sortable="true"/>
                                        <display:column property="kodUrusan.nama" title="Urusan" sortable="true"/>

                                        <display:column title="Baiki">
                                            <div align="center">
                                                <p align="center">
                                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                         onclick="viewUrusanBatal('${line.idUrusan}');
                return false;" onmouseover="this.style.cursor = 'pointer';">
                                                </p>
                                            </div>
                                        </display:column>
                                        <display:column title="Baiki Urusan Terperinci">
                                            <p align="center">

                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                     onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?updateUrusanTerperinci&idUrusan=${line.idUrusan}&idHakmilik=${line.hakmilik.idHakmilik}');"  onmouseover="this.style.cursor = 'pointer';">
                                            </p>
                                        </display:column>
                                        <display:column title="Hapus Pembetulan">
                                            <div align="center">
                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id='rem${line_rowNum}' onclick="removeChanges2('${line.idUrusan}')" onmouseover="this.style.cursor = 'pointer';" >
                                            </div>
                                        </display:column>
                                            
                                        <%--display:column title="Hapus Urusan">  #23012020 comment by azizi sbb user suka2 delete urusan Uncomment bila perlu.
                                            <div align="center">
                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id='rem${line_rowNum}' onclick="removeTotal('${line.idUrusan}')" onmouseover="this.style.cursor = 'pointer';" >
                                            </div>
                                        </display:column--%>
                                        
                                    </display:table>

                                </div>
                            </fieldset>
                        </td>
                    </tr>
                    <%--end of senarai Urusan Suratcara--%>
                    <tr onclick="showMe('B')" onmouseover="this.style.cursor = 'pointer';
                this.style.text" class="B"><th><span class="arrow">Senarai Urusan Borang (B)</span></th></tr>
                    <tr id="B">
                        <td>
                            <fieldset class="aras1">
                                <legend>
                                </legend>
                                <p style="color:red">
                                    *Klik butang (<img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif'/>) untuk membuat pembetulan.<br/>
                                </p>
                                <div class="content" align="center">
                                    <p style="color:blue">
                                        <b>Urusan Berkuatkuasa </b><br/>
                                    </p>
                                    <display:table class="tablecloth" name="${actionBean.hakmilikUrusanB}"  cellpadding="0" cellspacing="0" id="line"
                                                   requestURI="${pageContext.request.contextPath}/common/pihak" pagesize="10">
                                        <display:column title="Bil">${line_rowNum}</display:column>
                                        <display:column property="idPerserahan" title="ID Perserahan" sortable="true"/>
                                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" sortable="true"/>
                                        <display:column property="kodUrusan.kod" title="Kod Urusan" sortable="true"/>
                                        <display:column property="kodUrusan.nama" title="Urusan" sortable="true"/>

                                        <display:column title="Baiki">
                                            <div align="center">
                                                <p align="center">
                                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                         onclick="viewUrusan('${line.idUrusan}');
                return false;" onmouseover="this.style.cursor = 'pointer';">
                                                </p>
                                            </div>
                                        </display:column>
                                        <display:column title="Baiki Urusan Terperinci">
                                            <p align="center">

                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                     onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?updateUrusanTerperinci&idUrusan=${line.idUrusan}&idHakmilik=${line.hakmilik.idHakmilik}');"  onmouseover="this.style.cursor = 'pointer';">
                                            </p>
                                        </display:column>
                                        <display:column title="Hapus Pembetulan">
                                            <div align="center">
                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id='rem${line_rowNum}' onclick="removeChanges2('${line.idUrusan}')" onmouseover="this.style.cursor = 'pointer';" >
                                            </div>
                                        </display:column>
                                            
                                        <%--display:column title="Hapus Urusan"> #23012020 comment by azizi sbb user suka2 delete urusan Uncomment bila perlu.
                                            <div align="center">
                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id='rem${line_rowNum}' onclick="removeTotal('${line.idUrusan}')" onmouseover="this.style.cursor = 'pointer';" >
                                            </div>
                                        </display:column--%>
                                        
                                    </display:table>

                                    <p style="color:blue">
                                        <br/><b>Urusan Tidak Berkuatkuasa </b><br/>
                                    </p>
                                    <display:table class="tablecloth" name="${actionBean.hakmilikUrusanBbatal}"  cellpadding="0" cellspacing="0" id="line"
                                                   requestURI="${pageContext.request.contextPath}/common/pihak" pagesize="10">
                                        <display:column title="Bil">${line_rowNum}</display:column>
                                        <display:column property="idPerserahan" title="ID Perserahan" sortable="true"/>
                                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" sortable="true"/>
                                        <display:column property="kodUrusan.kod" title="Kod Urusan" sortable="true"/>
                                        <display:column property="kodUrusan.nama" title="Urusan" sortable="true"/>

                                        <display:column title="Baiki">
                                            <div align="center">
                                                <p align="center">
                                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                         onclick="viewUrusanBatal('${line.idUrusan}');
                return false;" onmouseover="this.style.cursor = 'pointer';">
                                                </p>
                                            </div>
                                        </display:column>
                                        <display:column title="Baiki Urusan Terperinci">
                                            <p align="center">

                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                     onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?updateUrusanTerperinci&idUrusan=${line.idUrusan}&idHakmilik=${line.hakmilik.idHakmilik}');"  onmouseover="this.style.cursor = 'pointer';">
                                            </p>
                                        </display:column>
                                        <display:column title="Hapus Pembetulan">
                                            <div align="center">
                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id='rem${line_rowNum}' onclick="removeChanges2('${line.idUrusan}')" onmouseover="this.style.cursor = 'pointer';" >
                                            </div>
                                        </display:column>
                                            
                                        <%--display:column title="Hapus Urusan"> #23012020 comment by azizi sbb user suka2 delete urusan Uncomment bila perlu.
                                            <div align="center">
                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id='rem${line_rowNum}' onclick="removeTotal('${line.idUrusan}')" onmouseover="this.style.cursor = 'pointer';" >
                                            </div>
                                        </display:column--%>

                                    </display:table>
                                </div>
                            </fieldset>
                        </td>
                    </tr>
                    <%--end of senarai Urusan Borang--%>
                    <tr onclick="showMe('N')" onmouseover="this.style.cursor = 'pointer';
                this.style.text" class="B"><th><span class="arrow">Senarai Urusan Nota/Fail (N / FL)</span></th></tr>
                    <tr id="N">
                        <td>
                            <fieldset class="aras1">
                                <legend>
                                </legend>
                                <p style="color:red">
                                    *Klik butang (<img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif'/>) untuk membuat pembetulan.<br/>
                                </p>
                                <div class="content" align="center">
                                   <p style="color:blue">
                                        <b>Urusan Berkuatkuasa </b><br/>
                                    </p>    
                              <display:table class="tablecloth" name="${actionBean.hakmilikUrusanN}"  cellpadding="0" cellspacing="0" id="line"
                                                   requestURI="${pageContext.request.contextPath}/common/pihak" >
                                        <display:column title="Bil">${line_rowNum}</display:column>
                                        <display:column property="idPerserahan" title="ID Perserahan" sortable="true"/>
                                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" sortable="true"/>
                                        <display:column property="kodUrusan.kod" title="Kod Urusan" sortable="true"/>
                                        <display:column property="kodUrusan.nama" title="Urusan" sortable="true"/>
                                        <display:column title="Baiki">
                                            <p align="center">
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                     onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?viewUrusan&idUrusan=${line.idUrusan}');"  onmouseover="this.style.cursor = 'pointer';">
                                            </p>
                                        </display:column>
                                        <display:column title="Baiki Urusan Terperinci">
                                            <p align="center">

                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                     onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?updateUrusanTerperinci&idUrusan=${line.idUrusan}&idHakmilik=${line.hakmilik.idHakmilik}');"  onmouseover="this.style.cursor = 'pointer';">
                                            </p>
                                        </display:column>
                                        <display:column title="Hapus Pembetulan">
                                            <div align="center">
                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id='rem${line_rowNum}' onclick="removeChanges2('${line.idUrusan}')" onmouseover="this.style.cursor = 'pointer';" >
                                            </div>
                                        </display:column>
                                            
                                        <%--display:column title="Hapus Urusan"> #23012020 comment by azizi sbb user suka2 delete urusan Uncomment bila perlu.
                                            <div align="center">
                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id='rem${line_rowNum}' onclick="removeTotal('${line.idUrusan}')" onmouseover="this.style.cursor = 'pointer';" >
                                            </div>
                                        </display:column--%>
                                        
                                    </display:table>
                                            
                                    <p style="color:blue">
                                        <br/><b>Urusan Tidak Berkuatkuasa </b><br/>
                                    </p>
                                    <display:table class="tablecloth" name="${actionBean.hakmilikUrusanNbatal}"  cellpadding="0" cellspacing="0" id="line"
                                                   requestURI="${pageContext.request.contextPath}/common/pihak" >
                                        <display:column title="Bil">${line_rowNum}</display:column>
                                        <display:column property="idPerserahan" title="ID Perserahan" sortable="true"/>
                                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" sortable="true"/>
                                        <display:column property="kodUrusan.kod" title="Kod Urusan" sortable="true"/>
                                        <display:column property="kodUrusan.nama" title="Urusan" sortable="true"/>

                                        <display:column title="Baiki">
                                            <div align="center">
                                                <p align="center">
                                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                         onclick="viewUrusanBatal('${line.idUrusan}');
                                                         return false;" onmouseover="this.style.cursor = 'pointer';">
                                                </p>
                                            </div>
                                        </display:column>
                                        <display:column title="Baiki Urusan Terperinci">
                                            <p align="center">

                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                     onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?updateUrusanTerperinci&idUrusan=${line.idUrusan}&idHakmilik=${line.hakmilik.idHakmilik}');"  onmouseover="this.style.cursor = 'pointer';">
                                            </p>
                                        </display:column>
                                        <display:column title="Hapus Pembetulan">
                                            <div align="center">
                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id='rem${line_rowNum}' onclick="removeChanges2('${line.idUrusan}')" onmouseover="this.style.cursor = 'pointer';" >
                                            </div>
                                        </display:column>
                                            
                                        <%--display:column title="Hapus Urusan"> #23012020 comment by azizi sbb user suka2 delete urusan Uncomment bila perlu.
                                            <div align="center">
                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id='rem${line_rowNum}' onclick="removeTotal('${line.idUrusan}')" onmouseover="this.style.cursor = 'pointer';" >
                                            </div>
                                        </display:column--%>

                                    </display:table>
                                </div>
                            </fieldset>
                        </td>
                    </tr>
                    <%--end of senarai Urusan Nota/FL--%>
                </table>


                <br/></div>
        </fieldset>


        <br/>

        <fieldset class="aras1">
            <legend>
                Tambah Urusan
            </legend>
            <br>
            <c:if test="${actionBean.listUrusanBetul != null}">
                
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.listUrusanBetul}" cellpadding="0" cellspacing="0" id="line">
                        <display:column>
                            <s:hidden name="x" class="x${line_rowNum -1}" value="${line.idPembetulan}"/>
                        </display:column>
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column property="idPerserahanLama" title="ID Perserahan" sortable="true"/>
                        <display:column property="hakmilik.hakmilik.idHakmilik" title="ID Hakmilik" sortable="true"/>
                        <display:column property="kodUrusan.kod" title="Kod Urusan" sortable="true"/>
                        <display:column property="kodUrusan.nama" title="Urusan" sortable="true"/>
                        <display:column property="noJilid" title="No Jilid"/>
                        <display:column property="noFolio" title="No Folio"/>
                        <display:column property="tarikhDaftar" title="Tarikh Daftar" format="{0,date,dd-MM-yyyy hh:mm:ss aaa}"/>
                        <display:column title="Kemaskini">
                            <p align="center">

                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?updateUrusan&idPembetulan=${line.idPembetulan}&idHakmilik=${line.hakmilik.hakmilik.idHakmilik}&idPerserahanLama=${line.idPerserahanLama}');"  onmouseover="this.style.cursor = 'pointer';">
                            </p>
                        </display:column>
                        <display:column title="Tambah Pihak">
                            <p align="center">
                                <img alt='Klik Untuk Tambah Pihak' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?tambahPihak&idPembetulan=${line.idPembetulan}');"  onmouseover="this.style.cursor = 'pointer';">
                            </p>
                        </display:column>
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem${line_rowNum}' onclick="removeChanges('${line.idPembetulan}', '${line.hakmilik.hakmilik.idHakmilik}')" onmouseover="this.style.cursor = 'pointer';" >
                            </div>
                        </display:column>
                    </display:table>
                </div>
                </c:if>

            <table style="margin-left: auto; margin-right: auto;">
                <tr>
                    <td><div >
                            <br>
                            <s:button name="tambahurusan" value="Tambah Urusan" class="longbtn"  onclick="addUrusan('${actionBean.idPermohonan}');return false;"/>
                        </div>
                    </td>
                </tr>
            </table>

            <br/>
        </fieldset>


    </s:form>
</div>