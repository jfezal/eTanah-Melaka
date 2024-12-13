<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
        <title>Semakan Dokumen </title>

    </head>
    <body>

        <script language="javascript">

            function popupParam(nama,idhakmilik){
                window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+"&reportName="+nama+"&report_p_id_hakmilik="+idhakmilik, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
            }

            function doPrintViaApplet (docId) {
                var a = document.getElementById('applet');
                a.doPrint(docId.toString());
                //a.printDocument(docId.toString());
            }

            function doPrintBelakangResit(caraByrId){
                var a = document.getElementById('applet2');
                a.printChequeInfo(caraByrId.toString());
            }
 
            function doViewReport(v) {
                var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
                window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
            }
            function doEdit(v) {
                window.open("${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?maklumatDetail&idHakmilik="+ v, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1200,height=800,scrollbars=yes");
            }
            function zeroPad(num, count) {
                var numZeropad = num + '';
                while (numZeropad.length < count) {
                    numZeropad = "0" + numZeropad;
                }
                return numZeropad;
            }
    
            function doViewPelan(v,ks){

                var kodDaerah = v.substring(2,4);
                var kodBPM2 = $('#kodBPM').val();
                var kodBPM = zeroPad(kodBPM2, 2);
                var kodNegeri = v.substring(0,2);
                var kodSeksyen = ks;
                if (kodSeksyen === "") {
                    kodSeksyen = "000";
                }

                var noLot = $('#noLot').val();
                var jenisPelan;
                var kodPelan;
                var jenisHm = $('#pegangan').val();

                if (jenisHm === 'S') {
                    jenisPelan = "B1";
                    kodPelan = "1";
                } else {
                    jenisPelan = "B2";
                    kodPelan = "3";
                }

      
                var url = '${pageContext.request.contextPath}/pelan/view/' + kodNegeri + kodDaerah + kodBPM + kodSeksyen + kodPelan + noLot;
                window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
                
            }
            
            

        </script>

        <p class=title>Langkah 2 : Semakan Dokumen </p>            
        <stripes:messages />
        <stripes:errors />
        <c:if test="${!empty tanpaBayaran}">
            <%--<c:if test="${empty actionBean.senaraiKandunganFolder2}">
                <span class=instr>* Sila pilih Kemaskini untuk mengemaskini data hakmilik dan pilih Jana Dokumen setelah selesai kemaskini data hakmilik</span><br/><br/>
            </c:if>--%>
            <c:if test="${!empty actionBean.senaraiKandunganFolder2}">
                <span class=instr>* Dokumen Telah Dijana, Sila Buat Semakan Sebelum ke Peringkat Seterusnya</span><br/><br/>
            </c:if>
        </c:if>

        <c:set var="printMultiple" value=""/>
        <%-- <div class="subtitle"><s:text name="kodNegeri" id="negeri" style="display:none;"/>
             <fieldset class="aras1">
                 <legend>Maklumat Pembayaran</legend>
                 <p>
                     <div class="content" align="center">--%>

        <c:if test="${!empty actionBean.senaraiKandunganFolder2}">

            <display:table  name="${actionBean.senaraiKandunganFolder2}" id="row" class="tablecloth" >
                <display:column title="Bil" >${row_rowNum}</display:column>
                <display:column title="Kod Dokumen">
                    ${row.dokumen.kodDokumen.kod}
                </display:column>
                <display:column title="Nama Dokumen">
                    ${row.dokumen.tajuk}
                </display:column>
                <display:column title="Papar" >
                    <button name="cetakPP${row_rowNum}"
                            onclick="doViewReport('${row.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                            onmouseover="this.style.cursor='pointer';" title="Papar ${row.dokumen.kodDokumen.nama}"
                            class="btn">Papar</button>&nbsp;
                </display:column>

                <%--              <c:if test="${row.dokumen.namaFizikal != null}">
                              <display:column title="Cetakan Carian">
                                      <button name="cetakPP${row_rowNum}"
                                              onclick="doPrintViaApplet('${row.dokumen}');"
                                              class="btn">Cetak</button>&nbsp;
                                   <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                               onclick="doPrintViaApplet('${row.dokumen.idDokumen}');" height="30" width="30" alt="cetak"
                                               onmouseover="this.style.cursor='pointer';" title="Cetak Dokumen ${row.dokumen.kodDokumen.nama}"/>
                              </display:column>
                              </c:if>  --%>
            </display:table>
        </c:if>
        <br/>

        <c:set var="action" value="/daftar/carian_strata"/>
        <c:if test="${!empty tanpaBayaran}">
            <c:set var="action" value="/daftar/carian_strata"/>
        </c:if>
        <stripes:form action="${action}">
            <stripes:hidden name="semula" value="Y"/>&nbsp;
            <stripes:hidden id="idFolder" name="idFolder" value="${actionBean.idFolder}"/>
            <stripes:hidden id="noLot" name="noLot" value="${actionBean.noLot}"/>
            <stripes:hidden id="pegangan" name="pegangan" value="${actionBean.pegangan}"/>
            <stripes:hidden id="kodBPM" name="kodBPM" value="${actionBean.kodBPM}"/>

            <%--<stripes:submit name="Step3" value="Jana Dokumen" class="longbtn"/>--%>
            <%--<c:if test="${!empty actionBean.senaraiKandunganFolder2}">
                <stripes:submit name="Step4" value="Daftar versi 1" class="longbtn"/>
            </c:if>--%>
            <%--<stripes:submit name="kemaskini" value="Kemaskini" class="longbtn"/>--%>

            <%--<c:if test="${fn:length (actionBean.hakmilikPermohonan) > 1}">--%>
            <%--<stripes:button name="Step3b" onclick="doEdit(idHakmilik.value)" value="Kemaskini" class="longbtn"/>--%>
            <%--<stripes:button name="Step5"  value="Papar Pelan" class="longbtn"  onclick="doViewPelan(idHakmilik.value,'${actionBean.hakmilik.seksyen.seksyen}')"/>--%>
            <%-- <stripes:button name="Step3b"  value="Kemaskini" class="longbtn"  onclick="doEdit(idHakmilik.value)"/>
         <tr>
             <td id="tdLabel">*Sila pilih ID Hakmilik untuk dikemaskini :&nbsp</td>
             <td id="tdDisplay">
                 <s:select name="idHakmilik" id="idHakmilik" style="width:225px;">
                     <s:options-collection collection="${actionBean.hakmilikPermohonan}" label="idHakmilik" value="idHakmilik" />
                 </s:select>
             </td>
         </tr>--%>
            <%--</c:if>--%>
            <%--<c:if test="${fn:length (actionBean.hakmilikPermohonan) eq 1}">
                <stripes:button name="Step3b" onclick="doEdit('${actionBean.idHakmilik}')" value="Kemaskini" class="longbtn"/>
                <stripes:button name="Step5"  value="Papar Pelan" class="longbtn"  onclick="doViewPelan('${actionBean.idHakmilik}','${actionBean.hakmilik.seksyen.seksyen}')"/>
            </c:if>--%>
            <c:if test="${actionBean.idHakmilikInduk ne null && actionBean.kodNeg eq 'n9'}">
                <c:if test="${actionBean.hakmilik.noVersiIndeksStrata eq '0' || actionBean.hakmilik.noVersiIndeksStrata eq null}">
                    <table class="tablecloth" align="center">
                        <tr style="width: 100%">
                            <th>Senarai Borang</th>
                            <th>Id Hakmilik</th>
                            <th>Papar</th>
                        </tr>
                        <tr>
                            <td>Borang 3(K)</td>
                            <td>${actionBean.idHakmilikInduk}</td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popupParam('UTILITIB3K_NS','${actionBean.idHakmilikInduk}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Borang 2(K)</td>
                            <td>${actionBean.idHakmilikInduk}</td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popupParam('UTILITIB2K_NS','${actionBean.idHakmilikInduk}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                    </table>
                    <br>
                </c:if>
            </c:if>

            <c:if test="${actionBean.idHakmilikInduk ne null && actionBean.kodNeg eq 'melaka'}">
                <c:if test="${actionBean.hakmilik.noVersiIndeksStrata eq '0' || actionBean.hakmilik.noVersiIndeksStrata eq null}">
                    <table class="tablecloth" align="center">
                        <tr style="width: 100%">
                            <th>Senarai Borang</th>
                            <th>Id Hakmilik</th>
                            <th>Papar</th>
                        </tr>
                        <tr>
                            <td>Borang 3(K)</td>
                            <td>${actionBean.idHakmilikInduk}</td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popupParam('UTILITIB3K_MLK','${actionBean.idHakmilikInduk}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Borang 2(K)</td>
                            <td>${actionBean.idHakmilikInduk}</td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popupParam('UTILITIB2K_MLK','${actionBean.idHakmilikInduk}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                    </table>
                    <br>
                </c:if>
            </c:if>

            <c:if test="${actionBean.kodNeg eq 'melaka'}">
                <display:table style="width:90%" class="tablecloth" name="${actionBean.listhakmilikPermohonan}" pagesize="1000" cellpadding="0" cellspacing="0" id="line2">
                    <display:column title="Bil">${line2_rowNum}</display:column>
                    <c:if test="${line2.kodKategoriBangunan.kod eq 'P'}">
                        <display:column title="Id Hakmilik Strata">${line2.idHakmilikInduk} - Bangunan Sementara - ${line2.noBangunan}</display:column>
                    </c:if>
                    <c:if test="${line2.kodKategoriBangunan.kod ne 'P'}">
                        <display:column title="Id Hakmilik Strata">${line2.idHakmilik}</display:column>
                    </c:if>
                    <display:column title="Papar">
                        <c:if test="${line2.kodKategoriBangunan.kod eq 'P'}">
                            Borang 4AK(DHDK)
                            <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                 onclick="popupParam('UTILITIB4AKDHDK_MLK','${line2.idHakmilik}');" onmouseover="this.style.cursor='pointer';"><br>
                            Borang 4AK(DHKK)
                            <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                 onclick="popupParam('UTILITIB4AKDHKK_MLK','${line2.idHakmilik}');" onmouseover="this.style.cursor='pointer';"><br>
                        </c:if>
                        <c:if test="${line2.kodKategoriBangunan.kod ne 'P'}">
                                Borang 4K(DHDK)
                                <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="popupParam('UTILITIB4KDHDK_MLK','${line2.idHakmilik}');" onmouseover="this.style.cursor='pointer';"><br>
                                Borang 4K(DHKK)
                                <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="popupParam('UTILITIB4KDHKK_MLK','${line2.idHakmilik}');" onmouseover="this.style.cursor='pointer';"><br>
                        </c:if>
                        Borang SK(DHDK)
                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                             onclick="popupParam('UTILITIBSKDHDK_MLK','${line2.idHakmilik}');" onmouseover="this.style.cursor='pointer';"><br>
                        Borang SK(DHKK)
                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                             onclick="popupParam('UTILITIBSKDHKK_MLK','${line2.idHakmilik}');" onmouseover="this.style.cursor='pointer';"><br>
                        Borang SK(Pelan Akui)
                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                             onclick="popupParam('UTILITIPSK_MLK.rdf','${line2.idHakmilik}');" onmouseover="this.style.cursor='pointer';">
                    </display:column>
                </display:table>
            </c:if>

            <c:if test="${actionBean.kodNeg eq 'n9'}">
                <display:table style="width:90%" class="tablecloth" name="${actionBean.listhakmilikPermohonan}" pagesize="1000" cellpadding="0" cellspacing="0" id="line2">
                    <display:column title="Bil">${line2_rowNum}</display:column>
                    <c:if test="${line2.kodKategoriBangunan.kod eq 'P'}">
                        <display:column title="Id Hakmilik Strata">${line2.idHakmilikInduk} - Bangunan Sementara - ${line2.noBangunan}</display:column>
                    </c:if>
                    <c:if test="${line2.kodKategoriBangunan.kod ne 'P'}">
                        <display:column title="Id Hakmilik Strata">${line2.idHakmilik}</display:column>
                    </c:if>
                    <display:column title="Papar">
                        <c:if test="${line2.kodKategoriBangunan.kod eq 'P'}">

                            Borang 4AK(DHDK)
                            <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                 onclick="popupParam('UTILITIB4AKDHDK_NS','${line2.idHakmilik}');" onmouseover="this.style.cursor='pointer';"><br>
                            Borang 4AK(DHKK)
                            <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                 onclick="popupParam('UTILITIB4AKDHKK_NS','${line2.idHakmilik}');" onmouseover="this.style.cursor='pointer';"><br>
                        </c:if>
                        <c:if test="${line2.kodKategoriBangunan.kod ne 'P'}">
                                Borang 4K(DHDK)
                                <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="popupParam('UTILITIB4KDHDK_NS','${line2.idHakmilik}');" onmouseover="this.style.cursor='pointer';"><br>
                                Borang 4K(DHKK)
                                <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="popupParam('UTILITIB4KDHKK_NS','${line2.idHakmilik}');" onmouseover="this.style.cursor='pointer';"><br>
                        </c:if>
                        Borang SK(DHDK)
                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                             onclick="popupParam('UTILITIBSKDHDK_NS','${line2.idHakmilik}');" onmouseover="this.style.cursor='pointer';"><br>
                        Borang SK(DHKK)
                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                             onclick="popupParam('UTILITIBSKDHKK_NS','${line2.idHakmilik}');" onmouseover="this.style.cursor='pointer';"><br>
                        Borang SK(Pelan Akui)
                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                             onclick="popupParam('UTILITIPSK_NS.rdf','${line2.idHakmilik}');" onmouseover="this.style.cursor='pointer';">
                    </display:column>
                </display:table>
            </c:if>

            <br><br>
            <stripes:submit name="Step1" value="Kembali ke Utama" class="longbtn"/>
            <stripes:submit name="Step4" value="Daftar versi 1" class="longbtn"/>




        </stripes:form>


        <applet code="PrintApplet.class" ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
                ${pageContext.request.contextPath}/commons-httpclient-2.0.2.jar,
                ${pageContext.request.contextPath}/commons-logging.jar,
                ${pageContext.request.contextPath}/swingx-1.6.jar,
                ${pageContext.request.contextPath}/log4j-1.2.12.jar,
                ${pageContext.request.contextPath}/jpedal_demo.jar"
                codebase = "."
                name     = "applet"
                id       = "applet"
                width    = "1"
                height   = "1"
                align    = "middle">
            <param name="uploadAction" value="<%=request.getContextPath()%>/PrintServlet"/>
            <param name ="kodNegeri" value="05">
            <param name ="method" value="pdfp">
            <param name ="disabledWatermark" value="true"/>
            <param name ="idPengguna" value="${idPengguna}"/>
            <%
                        Cookie[] cookies = request.getCookies();
                        StringBuffer sb = new StringBuffer();
                        for (int i = 0; i < cookies.length; i++) {
                            sb.setLength(0);
                            sb.append(cookies[i].getName());
                            sb.append("=");
                            sb.append(cookies[i].getValue());
            %>
            <param name="Cookie<%= i%>" value="<%= sb.toString()%>"><%
                        }
            %>
        </applet>
        <%--<applet code="PrintApplet.class" ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
                ${pageContext.request.contextPath}/commons-httpclient-2.0.2.jar,
                ${pageContext.request.contextPath}/commons-logging.jar,
                ${pageContext.request.contextPath}/swingx-1.6.jar,
                ${pageContext.request.contextPath}/log4j-1.2.12.jar,
                ${pageContext.request.contextPath}/jpedal_demo.jar"
                codebase = "."
                name     = "applet"
                id       = "applet"
                width    = "1"
                height   = "1"
                align    = "middle">
            <param name="uploadAction" value="<%=request.getContextPath()%>/PrintServlet"/>
            <param name ="kodNegeri" value="05">
            <param name ="method" value="pdfp">
            <param name ="disabledWatermark" value="true"/>
            <param name ="idPengguna" value="${idPengguna}"/>
            <%
                Cookie[] cookies = request.getCookies();
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < cookies.length; i++) {
                    sb.setLength(0);
                    sb.append(cookies[i].getName());
                    sb.append("=");
                    sb.append(cookies[i].getValue());
            %>
            <param name="Cookie<%= i%>" value="<%= sb.toString()%>"><%
                }
            %>
        </applet>--%>

        <applet code="etanah.dokumen.print.PrinterHasil" ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
                ${pageContext.request.contextPath}/commons-httpclient-2.0.2.jar,
                ${pageContext.request.contextPath}/commons-logging.jar,
                ${pageContext.request.contextPath}/swingx-1.6.jar,
                ${pageContext.request.contextPath}/log4j-1.2.12.jar,
                ${pageContext.request.contextPath}/jpedal_demo.jar"
                codebase = "${pageContext.request.contextPath}/"
                name     = "applet2"
                id       = "applet2"
                width    = "1"
                height   = "1"
                align    = "middle">
            <param name="uploadAction" value="<%=request.getContextPath()%>/PrintServlet"/>
            <param name="kod_negeri" value="${negeri}"/>
            <param name ="method" value="pdfp">
            <%
                        Cookie[] cookies2 = request.getCookies();
                        StringBuffer sb2 = new StringBuffer();
                        for (int i = 0; i < cookies2.length; i++) {
                            sb2.setLength(0);
                            sb2.append(cookies2[i].getName());
                            sb2.append("=");
                            sb2.append(cookies2[i].getValue());
            %>
            <param name="Cookie<%= i%>" value="<%= sb2.toString()%>"><%
                        }
            %>
        </applet>

    </body>
</html>