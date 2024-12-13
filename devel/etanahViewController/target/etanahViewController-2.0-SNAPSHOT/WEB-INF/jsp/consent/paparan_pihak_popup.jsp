<%-- 
    Document   : paparan_pihak
    Created on : Jun 27, 2010, 2:23:33 PM
    Author     : muhammad.amin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>

<s:form beanclass="etanah.view.stripes.consent.PihakKepentinganActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                <c:if test="${penerima}"> 
                    <c:choose>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PCPTD' || actionBean.permohonan.kodUrusan.kod eq 'PGDMB' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'PCMMK' || actionBean.permohonan.kodUrusan.kod eq 'GSMMK' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'PMJTL' || actionBean.permohonan.kodUrusan.kod eq 'PJKTL' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'PMTMB' || actionBean.permohonan.kodUrusan.kod eq 'PMMAT' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'RMJTL' || actionBean.permohonan.kodUrusan.kod eq 'RJKTL' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'PMWNA' || actionBean.permohonan.kodUrusan.kod eq 'PMWWA'
                                        || actionBean.permohonan.kodUrusan.kod eq 'PPTGM' || actionBean.permohonan.kodUrusan.kod eq 'PMKMM'
                                        || actionBean.permohonan.kodUrusan.kod eq 'PMPTD' || actionBean.permohonan.kodUrusan.kod eq 'PMMMK'
                                        || fn:startsWith(actionBean.permohonan.kodUrusan.kod, 'PPTD') ||  fn:startsWith(actionBean.permohonan.kodUrusan.kod, 'PMMK')}">
                                Maklumat Penerima Pindah Milik
                        </c:when>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PJPTD' || actionBean.permohonan.kodUrusan.kod eq 'PJMMK' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'PJADT' || actionBean.permohonan.kodUrusan.kod eq 'PJKMM'}">
                                Maklumat Penerima Pajakan
                        </c:when>
                        <c:when  test="${fn:startsWith(actionBean.permohonan.kodUrusan.kod,'GPTD') || actionBean.permohonan.kodUrusan.kod eq 'MCGMB' 
                                         || actionBean.permohonan.kodUrusan.kod eq 'MCMMK' || actionBean.permohonan.kodUrusan.kod eq 'CGADT'}">
                                 Maklumat Penerima Gadaian
                        </c:when>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'BTADT' || actionBean.permohonan.kodUrusan.kod eq 'TTADT'}">
                            Maklumat Pihak Yang Menuntut
                        </c:when>                        
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'MCKMM' || actionBean.permohonan.kodUrusan.kod eq 'MCPTG' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'MCPTD'}">
                            Maklumat Penerima Cagaran
                        </c:when>    
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'SWKMM' || actionBean.permohonan.kodUrusan.kod eq 'SWPTD'}">
                            Maklumat Penerima Sewaan
                        </c:when>    
                        <c:when test="${(actionBean.permohonan.kodUrusan.kod eq 'PMMK1' && actionBean.kodNegeri eq '04') || (actionBean.permohonan.kodUrusan.kod eq 'PMMK2' && actionBean.kodNegeri eq '04')}">
                            Maklumat Pemohon/Penerima
                        </c:when>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PMMAT' || actionBean.permohonan.kodUrusan.kod eq 'DPWNA'}">
                            Penerima Turun Milik
                        </c:when>  
                        <c:otherwise>
                            Maklumat Penerima
                        </c:otherwise>
                    </c:choose>

                </c:if>
                <c:if test="${pemohon}">Maklumat Pemohon</c:if>
                <c:if test="${tuanTanah}">Maklumat Tuan Tanah</c:if>
                <c:if test="${terlibat}">Maklumat Tuan Tanah Terlibat</c:if>
                </legend>
                <p>
                    <label>Nama :</label>
                <c:if test="${actionBean.hakmilikPihakB.nama ne null}"> <font style="text-transform:uppercase;"> ${actionBean.hakmilikPihakB.nama}&nbsp;</font> </c:if>
                <c:if test="${actionBean.permohonanPihak.nama ne null}"> <font style="text-transform:uppercase;"> ${actionBean.permohonanPihak.nama}&nbsp;</font> </c:if>
                <c:if test="${actionBean.hakmilikPihakB.nama eq null && actionBean.permohonanPihak.nama eq null}"> TIADA DATA </c:if>
                </p>
                <p>
                    <label>Jenis Pengenalan :</label>
                <c:if test="${actionBean.hakmilikPihakB.jenisPengenalan.nama ne null}">  <font style="text-transform:uppercase;">${actionBean.hakmilikPihakB.jenisPengenalan.nama}&nbsp;</font> </c:if>
                 <c:if test="${actionBean.permohonanPihak.jenisPengenalan.nama ne null}">  <font style="text-transform:uppercase;">${actionBean.permohonanPihak.jenisPengenalan.nama}&nbsp;</font> </c:if>
                <c:if test="${actionBean.hakmilikPihakB.jenisPengenalan.nama eq null && actionBean.permohonanPihak.jenisPengenalan.nama eq null}"> TIADA DATA </c:if>
                </p>
                <p>
                    <label>Nombor Pengenalan :</label>
                <c:if test="${actionBean.hakmilikPihakB.noPengenalan ne null}"> ${actionBean.hakmilikPihakB.noPengenalan}&nbsp; </c:if>
                <c:if test="${actionBean.permohonanPihak.noPengenalan ne null}"> ${actionBean.permohonanPihak.noPengenalan}&nbsp; </c:if>
                <c:if test="${actionBean.hakmilikPihakB.noPengenalan eq null && actionBean.permohonanPihak.noPengenalan eq null}"> TIADA DATA </c:if>
                </p>
                <p>
                    <label>Nombor Pengenalan Lain :</label>
                <c:if test="${actionBean.pihak.noPengenalanLain ne null}"> ${actionBean.pihak.noPengenalanLain}&nbsp; </c:if>
                <c:if test="${actionBean.pihak.noPengenalanLain eq null}"> TIADA DATA </c:if>
                </p>
                <p>
                    <label>
                    <%--  <c:choose>
                      <c:when test="${actionBean.hakmilikPihakB.jenisPengenalan.kod eq 'B' || actionBean.hakmilikPihakB.jenisPengenalan.kod eq 'L' || actionBean.hakmilikPihakB.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                            Bangsa :
                        </c:when>
                        <c:otherwise>
                            Jenis Syarikat :
                        </c:otherwise>
                    </c:choose>--%>
                </label>
                <%--<c:if test="${actionBean.hakmilikPihakB.bangsa.nama ne null}">  <font style="text-transform:uppercase;">${actionBean.hakmilikPihakB.bangsa.nama}&nbsp;</font> </c:if>
<c:if test="${actionBean.hakmilikPihakB.bangsa.nama eq null}"> TIADA DATA </c:if>--%>
                </p>
            <c:if test="${actionBean.pihak.tarikhMati ne null}">
                <p>
                    <label>Tarikh Mati :</label>
                    <c:if test="${actionBean.pihak.tarikhMati ne null}"><s:format formatPattern="dd/MM/yyyy" value="${actionBean.pihak.tarikhMati}"/>&nbsp;</c:if>
                    <c:if test="${actionBean.pihak.tarikhMati eq null}"> TIADA DATA </c:if>

                    </p>
                    <p>
                        <label>Nombor Sijil Mati :</label>
                    <c:if test="${actionBean.hakmilikPihakB.noSijilMati ne null}"> ${actionBean.hakmilikPihakB.noSijilMati}&nbsp; </c:if>
                    <c:if test="${actionBean.hakmilikPihakB.noSijilMati eq null}"> TIADA DATA </c:if>
                    </p>
            </c:if>
            <c:if test="${penerima}">
                <p>
                    <label>Jenis Penerima :</label>
                    <c:if test="${actionBean.permohonanPihak.jenis.nama ne null}"> <font style="text-transform:uppercase;"> ${actionBean.permohonanPihak.jenis.nama}&nbsp;</font> </c:if>
                    <c:if test="${actionBean.permohonanPihak.jenis.nama eq null}"> TIADA DATA </c:if>
                    </p>

                    <p>
                    <c:choose>
                        <c:when test="${actionBean.hakmilikPihakB.jenisPengenalan.kod eq 'B' || actionBean.hakmilikPihakB.jenisPengenalan.kod eq 'L' ||
                                        actionBean.hakmilikPihakB.jenisPengenalan.kod eq 'P' || actionBean.hakmilikPihakB.jenisPengenalan.kod eq 'T' ||
                                        actionBean.hakmilikPihakB.jenisPengenalan.kod eq 'I'}">
                                <label>Kewarganegaraan :</label>
                        </c:when>
                        <c:otherwise>
                            <label>Negara Syarikat :</label>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${actionBean.hakmilikPihakB.wargaNegara.nama ne null}"> <font style="text-transform:uppercase;"> ${actionBean.hakmilikPihakB.wargaNegara.nama}&nbsp; </font></c:if>
                    <c:if test="${actionBean.hakmilikPihakB.wargaNegara.nama eq null}"> TIADA DATA </c:if>
                    </p>

                <c:choose>
                    <c:when test="${actionBean.hakmilikPihakB.jenisPengenalan.kod eq 'B' || actionBean.hakmilikPihakB.jenisPengenalan.kod eq 'L' ||
                                    actionBean.hakmilikPihakB.jenisPengenalan.kod eq 'P' || actionBean.hakmilikPihakB.jenisPengenalan.kod eq 'T' ||
                                    actionBean.hakmilikPihakB.jenisPengenalan.kod eq 'I'}">


                            <c:if test="${actionBean.kodNegeri eq '05'}">
                                <p>
                                    <label for="Suku">Jenis Suku :</label>
                                    <c:if test="${actionBean.pihak.suku.nama ne null}"> <font style="text-transform:uppercase;"> ${actionBean.pihak.suku.nama}&nbsp;</font> </c:if>
                                    <c:if test="${actionBean.pihak.suku.nama eq null}"> TIADA DATA </c:if>
                                    </p>
                                    <p>
                                        <label for="subSuku">Pecahan Suku/Lengkongan :</label>
                                    <c:if test="${actionBean.pihak.subSuku ne null}"> <font style="text-transform:uppercase;"> ${actionBean.pihak.subSuku}&nbsp;</font> </c:if>
                                    <c:if test="${actionBean.pihak.subSuku eq null}"> TIADA DATA </c:if>
                                    </p>
                                    <p>
                                        <label for="perut">Perut :</label>
                                    <c:if test="${actionBean.pihak.keturunan ne null}"> <font style="text-transform:uppercase;"> ${actionBean.pihak.keturunan}&nbsp; </font></c:if>
                                    <c:if test="${actionBean.pihak.keturunan eq null}"> TIADA DATA </c:if>
                                    </p>
                                    <p>
                                        <label for="luak">Luak :</label>
                                    <c:if test="${actionBean.pihak.tempatSuku ne null}">  <font style="text-transform:uppercase;">${actionBean.pihak.tempatSuku}&nbsp;</font> </c:if>
                                    <c:if test="${actionBean.pihak.tempatSuku eq null}"> TIADA DATA </c:if>
                                    </p>
                            </c:if>
                            <p>
                                <label>Tempat Lahir :</label>
                                <c:if test="${actionBean.pihak.tempatLahir ne null}">  <font style="text-transform:uppercase;">${actionBean.pihak.tempatLahir}&nbsp;</font> </c:if>
                                <c:if test="${actionBean.pihak.tempatLahir eq null}"> TIADA DATA </c:if>
                                </p>
                                <p>
                                    <label>Tarikh Lahir :</label>
                                <c:if test="${actionBean.pihak.tarikhLahir ne null}"><s:format formatPattern="dd/MM/yyyy" value="${actionBean.pihak.tarikhLahir}"/>&nbsp;</c:if>
                                <c:if test="${actionBean.pihak.tarikhLahir eq null}"> TIADA DATA </c:if>

                                </p>
                                <p>
                                    <label>Umur :</label>
                                <c:if test="${actionBean.permohonanPihak.umur ne null}"> ${actionBean.permohonanPihak.umur}&nbsp; </c:if>
                                <c:if test="${actionBean.permohonanPihak.umur eq null}"> TIADA DATA </c:if>
                                </p>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PMADT' && actionBean.permohonan.kodUrusan.kod ne 'CGADT' && actionBean.permohonan.kodUrusan.kod ne 'PAADT' && actionBean.permohonan.kodUrusan.kod ne 'TMADT' && actionBean.permohonan.kodUrusan.kod ne 'BTADT'}">
                                <p>
                                    <label>Pekerjaan :</label>
                                    <c:if test="${actionBean.permohonanPihak.pekerjaan ne null}"> <font style="text-transform:uppercase;"> ${actionBean.permohonanPihak.pekerjaan}&nbsp; </font></c:if>
                                    <c:if test="${actionBean.permohonanPihak.pekerjaan eq null}"> TIADA DATA </c:if>
                                    </p>
                                    <p>
                                        <label>Pendapatan Bulanan :</label>
                                    <c:if test="${actionBean.permohonanPihak.kodMataWang ne null}"> <font style="text-transform:uppercase;"> ${actionBean.permohonanPihak.kodMataWang.nama}</font>&nbsp;</c:if>
                                    <c:if test="${actionBean.permohonanPihak.pendapatan ne null}"><s:format formatPattern="##,##0.00" value="${actionBean.permohonanPihak.pendapatan}"/>&nbsp; </c:if>
                                    <c:if test="${actionBean.permohonanPihak.pendapatan eq null}"> TIADA DATA </c:if>
                                    </p>
                                    <p>
                                        <label>Tanggungan :</label>
                                    <c:if test="${actionBean.permohonanPihak.tangungan ne null}">  ${actionBean.permohonanPihak.tangungan}&nbsp;</c:if>
                                    <c:if test="${actionBean.permohonanPihak.tangungan eq null}"> TIADA DATA </c:if>
                                    </p>
                            </c:if>
                            <p>
                                <label>Status Perkahwinan :</label>
                                <c:if test="${actionBean.permohonanPihak.statusKahwin ne null}"> <font style="text-transform:uppercase;"> ${actionBean.permohonanPihak.statusKahwin}&nbsp; </font></c:if>
                                <c:if test="${actionBean.permohonanPihak.statusKahwin eq null}"> TIADA DATA </c:if>
                                </p>
                            <c:if test="${(actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod eq 'PMMK1') || (actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod eq 'PMMK2')}">
                                <p>
                                    <label>Jantina :</label>
                                    <c:if test="${actionBean.pihak.kodJantina ne null}">
                                        <c:if test="${actionBean.pihak.kodJantina eq '1'}">
                                            LELAKI
                                        </c:if>
                                        <c:if test="${actionBean.pihak.kodJantina eq '2'}">
                                            PEREMPUAN
                                        </c:if>
                                    </c:if>
                                    <c:if test="${actionBean.pihak.kodJantina eq null}"> TIADA DATA </c:if>
                                    </p>
                                    <p>
                                        <label>Tempoh Mastautin (Tahun) :</label>
                                    <c:if test="${actionBean.pemohon.tempohMastautin ne null}"> ${actionBean.pemohon.tempohMastautin}&nbsp; </c:if>
                                    <c:if test="${actionBean.pemohon.tempohMastautin eq null}"> TIADA DATA </c:if>
                                    </p>


                            </c:if>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${(actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod eq 'PMMK1') || (actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod eq 'PMMK2')}">
                            <p>
                                <label>Tarikh Ditubuhkan :</label>
                                <c:if test="${actionBean.pihak.tarikhLahir ne null}"><s:format formatPattern="dd/MM/yyyy" value="${actionBean.pihak.tarikhLahir}"/>&nbsp;</c:if>
                                <c:if test="${actionBean.pihak.tarikhLahir eq null}"> TIADA DATA </c:if>

                                </p>
                        </c:if>
                    </c:otherwise>
                </c:choose>
                <p>
                    <label>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PMADT' && actionBean.permohonan.kodUrusan.kod ne 'CGADT' && actionBean.permohonan.kodUrusan.kod ne 'PAADT' && actionBean.permohonan.kodUrusan.kod ne 'TMADT' && actionBean.permohonan.kodUrusan.kod ne 'BTADT'}">
                            Hubungan Dengan Pemohon :
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMADT' || actionBean.permohonan.kodUrusan.kod eq 'CGADT' || actionBean.permohonan.kodUrusan.kod eq 'PAADT' || actionBean.permohonan.kodUrusan.kod eq 'TMADT' || actionBean.permohonan.kodUrusan.kod eq 'BTADT'}">

                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TMADT'}">
                                Hubungan Dengan Simati :
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'TMADT'}">
                                Hubungan Dengan Tuan Tanah :
                            </c:if>
                        </c:if>
                    </label>
                    <c:if test="${actionBean.permohonanPihak.kaitan ne null}"> <font style="text-transform:uppercase;"> ${actionBean.permohonanPihak.kaitan}&nbsp; </font></c:if>
                    <c:if test="${actionBean.permohonanPihak.kaitan eq null}"> TIADA DATA </c:if>
                    </p>
            </c:if>

            <c:if test="${pemohon}">

                <p>
                    <c:choose>
                        <c:when test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' ||
                                        actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' ||
                                        actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                                <label>Kewarganegaraan :</label>
                        </c:when>
                        <c:otherwise>
                            <label>Negara Syarikat :</label>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${actionBean.pihak.wargaNegara.nama ne null}"> <font style="text-transform:uppercase;"> ${actionBean.pihak.wargaNegara.nama}&nbsp; </font></c:if>
                    <c:if test="${actionBean.pihak.wargaNegara.nama eq null}"> TIADA DATA </c:if>
                    </p>

                <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                    <%--                    <p>
                                            <label>Kewarganegaraan :</label>
                                            <c:if test="${actionBean.pihak.wargaNegara.nama ne null}"> <font style="text-transform:uppercase;"> ${actionBean.pihak.wargaNegara.nama}&nbsp; </font></c:if>
                                            <c:if test="${actionBean.pihak.wargaNegara.nama eq null}"> TIADA DATA </c:if>
                                        </p>--%>
                    <c:if test="${actionBean.kodNegeri eq '05'}">
                        <p>
                            <label for="Suku">Jenis Suku :</label>
                            <c:if test="${actionBean.pihak.suku.nama ne null}"> <font style="text-transform:uppercase;"> ${actionBean.pihak.suku.nama}&nbsp;</font> </c:if>
                            <c:if test="${actionBean.pihak.suku.nama eq null}"> TIADA DATA </c:if>
                            </p>
                            <p>
                                <label for="subSuku">Pecahan Suku/Lengkongan :</label>
                            <c:if test="${actionBean.pihak.subSuku ne null}"> <font style="text-transform:uppercase;"> ${actionBean.pihak.subSuku}&nbsp;</font> </c:if>
                            <c:if test="${actionBean.pihak.subSuku eq null}"> TIADA DATA </c:if>
                            </p>
                            <p>
                                <label for="perut">Perut :</label>
                            <c:if test="${actionBean.pihak.keturunan ne null}"> <font style="text-transform:uppercase;"> ${actionBean.pihak.keturunan}&nbsp;</font> </c:if>
                            <c:if test="${actionBean.pihak.keturunan eq null}"> TIADA DATA </c:if>
                            </p>
                            <p>
                                <label for="luak">Luak :</label>
                            <c:if test="${actionBean.pihak.tempatSuku ne null}">  <font style="text-transform:uppercase;">${actionBean.pihak.tempatSuku}&nbsp; </font></c:if>
                            <c:if test="${actionBean.pihak.tempatSuku eq null}"> TIADA DATA </c:if>
                            </p>
                    </c:if>
                    <p>
                        <label>Tempat Lahir :</label>
                        <c:if test="${actionBean.pihak.tempatLahir ne null}"> <font style="text-transform:uppercase;"> ${actionBean.pihak.tempatLahir}&nbsp;</font> </c:if>
                        <c:if test="${actionBean.pihak.tempatLahir eq null}"> TIADA DATA </c:if>
                        </p>
                        <p>
                            <label>Tarikh Lahir :</label>
                        <c:if test="${actionBean.pihak.tarikhLahir ne null}"><s:format formatPattern="dd/MM/yyyy" value="${actionBean.pihak.tarikhLahir}"/>&nbsp;</c:if>
                        <c:if test="${actionBean.pihak.tarikhLahir eq null}"> TIADA DATA </c:if>
                        </p>
                        <p>
                            <label>Umur :</label>
                        <c:if test="${actionBean.pemohon.umur ne null}"> ${actionBean.pemohon.umur}&nbsp; </c:if>
                        <c:if test="${actionBean.pemohon.umur eq null}"> TIADA DATA </c:if>
                        </p>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PMADT' && actionBean.permohonan.kodUrusan.kod ne 'CGADT' && actionBean.permohonan.kodUrusan.kod ne 'PAADT' && actionBean.permohonan.kodUrusan.kod ne 'TMADT' && actionBean.permohonan.kodUrusan.kod ne 'BTADT'}">
                        <p>
                            <label>Pekerjaan :</label>
                            <c:if test="${actionBean.pemohon.pekerjaan ne null}"> <font style="text-transform:uppercase;"> ${actionBean.pemohon.pekerjaan}&nbsp;</font> </c:if>
                            <c:if test="${actionBean.pemohon.pekerjaan eq null}"> TIADA DATA </c:if>
                            </p>
                            <p>
                                <label>Pendapatan Bulanan :</label>
                            <c:if test="${actionBean.pemohon.matawang ne null}"> <font style="text-transform:uppercase;"> ${actionBean.pemohon.matawang.nama}</font>&nbsp;</c:if>
                            <c:if test="${actionBean.pemohon.pendapatan ne null}"><s:format formatPattern="##,##0.00" value="${actionBean.pemohon.pendapatan}"/>&nbsp; </c:if>
                            <c:if test="${actionBean.pemohon.pendapatan eq null}"> TIADA DATA </c:if>
                            </p>
                            <p>
                                <label>Tanggungan :</label>
                            <c:if test="${actionBean.pemohon.tanggungan ne null}"> ${actionBean.pemohon.tanggungan}&nbsp; </c:if>
                            <c:if test="${actionBean.pemohon.tanggungan eq null}"> TIADA DATA </c:if>
                            </p>
                    </c:if>
                    <p>
                        <label>Status Perkahwinan :</label>
                        <c:if test="${actionBean.pemohon.statusKahwin ne null}"> <font style="text-transform:uppercase;"> ${actionBean.pemohon.statusKahwin}&nbsp; </font></c:if>
                        <c:if test="${actionBean.pemohon.statusKahwin eq null}"> TIADA DATA </c:if>
                        </p>

                    <c:if test="${(actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod eq 'PMMK1') || (actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod eq 'PMMK2')}">
                        <p>
                            <label>Jantina :</label>
                            <c:if test="${actionBean.pihak.kodJantina ne null}">
                                <c:if test="${actionBean.pihak.kodJantina eq '1'}">
                                    LELAKI
                                </c:if>
                                <c:if test="${actionBean.pihak.kodJantina eq '2'}">
                                    PEREMPUAN
                                </c:if>
                            </c:if>
                            <c:if test="${actionBean.pihak.kodJantina eq null}"> TIADA DATA </c:if>
                            </p>
                            <p>
                                <label>Tempoh Mastautin (Tahun) :</label>
                            <c:if test="${actionBean.pemohon.tempohMastautin ne null}"> ${actionBean.pemohon.tempohMastautin}&nbsp; </c:if>
                            <c:if test="${actionBean.pemohon.tempohMastautin eq null}"> TIADA DATA </c:if>
                            </p>

                    </c:if>
                </c:if>
                <p><label>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PMADT' && actionBean.permohonan.kodUrusan.kod ne 'CGADT' && actionBean.permohonan.kodUrusan.kod ne 'PAADT' && actionBean.permohonan.kodUrusan.kod ne 'TMADT' && actionBean.permohonan.kodUrusan.kod ne 'BTADT'}">
                            Hubungan Dengan Penerima :
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMADT' || actionBean.permohonan.kodUrusan.kod eq 'CGADT' || actionBean.permohonan.kodUrusan.kod eq 'PAADT' || actionBean.permohonan.kodUrusan.kod eq 'TMADT' || actionBean.permohonan.kodUrusan.kod eq 'BTADT'}">
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TMADT'}">
                                Hubungan Dengan Simati :
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'TMADT'}">
                                Hubungan Dengan Tuan Tanah :
                            </c:if>
                        </c:if>
                    </label>
                    <c:if test="${actionBean.pemohon.kaitan ne null}"> <font style="text-transform:uppercase;"> ${actionBean.pemohon.kaitan}&nbsp; </font></c:if>
                    <c:if test="${actionBean.pemohon.kaitan eq null}"> TIADA DATA </c:if>
                    </p>
            </c:if>
            <c:if test="${tuanTanah || terlibat}">
                <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                    <p>
                        <label>Kewarganegaraan :</label>
                        <c:if test="${actionBean.pihak.wargaNegara.nama ne null}"> <font style="text-transform:uppercase;"> ${actionBean.pihak.wargaNegara.nama}&nbsp;</font> </c:if>
                        <c:if test="${actionBean.pihak.wargaNegara.nama eq null}"> TIADA DATA </c:if>
                        </p>
                    <c:if test="${actionBean.kodNegeri eq '05'}">
                        <c:if test="${actionBean.pihak.suku.nama ne null}">
                            <p>
                                <label for="Suku">Jenis Suku :</label>
                                <c:if test="${actionBean.pihak.suku.nama ne null}"> <font style="text-transform:uppercase;"> ${actionBean.pihak.suku.nama}&nbsp;</font> </c:if>
                                <c:if test="${actionBean.pihak.suku.nama eq null}"> TIADA DATA </c:if>
                                </p>
                        </c:if>
                        <c:if test="${actionBean.pihak.subSuku ne null}">
                            <p>
                                <label for="subSuku">Pecahan Suku/Lengkongan :</label>
                                <c:if test="${actionBean.pihak.subSuku ne null}"> <font style="text-transform:uppercase;"> ${actionBean.pihak.subSuku}&nbsp;</font> </c:if>
                                <c:if test="${actionBean.pihak.subSuku eq null}"> TIADA DATA </c:if>
                                </p>
                        </c:if>
                        <c:if test="${actionBean.pihak.keturunan ne null}">
                            <p>
                                <label for="perut">Perut :</label>
                                <c:if test="${actionBean.pihak.keturunan ne null}"> <font style="text-transform:uppercase;"> ${actionBean.pihak.keturunan}&nbsp;</font> </c:if>
                                <c:if test="${actionBean.pihak.keturunan eq null}"> TIADA DATA </c:if>
                                </p>
                        </c:if>
                        <c:if test="${actionBean.pihak.tempatSuku ne null}">
                            <p>
                                <label for="luak">Luak :</label>
                                <c:if test="${actionBean.pihak.tempatSuku ne null}">  <font style="text-transform:uppercase;">${actionBean.pihak.tempatSuku}&nbsp; </font></c:if>
                                <c:if test="${actionBean.pihak.tempatSuku eq null}"> TIADA DATA </c:if>
                                </p>
                        </c:if>
                    </c:if>
                </c:if>
            </c:if>

            <c:if test="${actionBean.pihak.alamat1 eq null}">
                <p>
                    <label>Alamat Berdaftar :</label>
                    TIADA DATA&nbsp;
                </p>
            </c:if>

            <c:if test="${actionBean.pihak.alamat1 ne null}">
                <p>
                    <label>Alamat Berdaftar :</label>
                    <font style="text-transform:uppercase;">${actionBean.pihak.alamat1}&nbsp;</font>
                </p>
            </c:if>
            <c:if test="${actionBean.pihak.alamat2 ne null}">
                <p>
                    <label> &nbsp;</label>
                    <font style="text-transform:uppercase;">${actionBean.pihak.alamat2}&nbsp;</font>
                </p>
            </c:if>
            <c:if test="${actionBean.pihak.alamat3 ne null}">
                <p>
                    <label> &nbsp;</label>
                    <font style="text-transform:uppercase;">${actionBean.pihak.alamat3}&nbsp;</font>
                </p>
            </c:if>
            <c:if test="${actionBean.pihak.alamat4 ne null}">
                <p>
                    <label> &nbsp;</label>
                    <font style="text-transform:uppercase;">${actionBean.pihak.alamat4}&nbsp;</font>
                </p>
            </c:if>
            <p>
                <label>Poskod :</label>
                <c:if test="${actionBean.pihak.poskod ne null}"> ${actionBean.pihak.poskod}&nbsp; </c:if>
                <c:if test="${actionBean.pihak.poskod eq null}"> TIADA DATA </c:if>
                </p>
                <p>
                    <label>Negeri :</label>
                <c:if test="${actionBean.pihak.negeri.nama ne null}"> <font style="text-transform:uppercase;">${actionBean.pihak.negeri.nama}</font>&nbsp; </c:if>
                <c:if test="${actionBean.pihak.negeri.nama eq null}"> TIADA DATA </c:if>
                </p>
            <c:if test="${actionBean.pihak.senaraiAlamatTamb[0].alamatKetiga1 ne null}">
                <p>
                    <label>Alamat Surat-Menyurat :</label>
                    <font style="text-transform:uppercase;">${actionBean.pihak.senaraiAlamatTamb[0].alamatKetiga1}&nbsp;</font>
                    <!--<font style="text-transform:uppercase;">${actionBean.pihak.suratAlamat1}&nbsp;</font>-->
                </p>
            </c:if>
            <c:if test="${actionBean.pihak.senaraiAlamatTamb[0].alamatKetiga2 ne null}">
                <p>
                    <label> &nbsp;</label>
                    <font style="text-transform:uppercase;">${actionBean.pihak.senaraiAlamatTamb[0].alamatKetiga2}&nbsp;</font>
                    <!--<font style="text-transform:uppercase;">${actionBean.pihak.suratAlamat2}&nbsp;</font>-->
                </p>
            </c:if>
            <c:if test="${actionBean.pihak.senaraiAlamatTamb[0].alamatKetiga3 ne null}">
                <p>
                    <label> &nbsp;</label>
                    <font style="text-transform:uppercase;">${actionBean.pihak.senaraiAlamatTamb[0].alamatKetiga3}&nbsp;</font>
                 <!--<font style="text-transform:uppercase;">${actionBean.pihak.suratAlamat3}&nbsp;</font>-->
                </p>
            </c:if>                
            <c:if test="${actionBean.pihak.senaraiAlamatTamb[0].alamatKetiga4 ne null}">
                <p>
                    <label>Bandar :</label>
                    <font style="text-transform:uppercase;">${actionBean.pihak.senaraiAlamatTamb[0].alamatKetiga4}&nbsp;</font>
                 <!--<font style="text-transform:uppercase;"> ${actionBean.pihak.suratAlamat4}&nbsp;</font>-->
                </p>
            </c:if>
            <c:if test="${actionBean.pihak.senaraiAlamatTamb[0].alamatKetigaPoskod ne null}">
                <p>
                    <label>Poskod :</label>
                    <c:if test="${actionBean.pihak.senaraiAlamatTamb[0].alamatKetigaPoskod ne null}"> ${actionBean.pihak.senaraiAlamatTamb[0].alamatKetigaPoskod}&nbsp; </c:if>
                    <c:if test="${actionBean.pihak.senaraiAlamatTamb[0].alamatKetigaPoskod eq null}"> TIADA DATA </c:if>
                    </p>
            </c:if>
            <c:if test="${actionBean.pihak.senaraiAlamatTamb[0].alamatKetigaNegeri ne null}">
                <p>
                    <label>Negeri :</label>
                    <c:if test="${actionBean.pihak.senaraiAlamatTamb[0].alamatKetigaNegeri ne null}"><font style="text-transform:uppercase;"> ${actionBean.pihak.senaraiAlamatTamb[0].alamatKetigaNegeri.nama}</font>&nbsp; </c:if>
                    <c:if test="${actionBean.pihak.senaraiAlamatTamb[0].alamatKetigaNegeri eq null}"> TIADA DATA </c:if>
                    </p>
            </c:if>

            <c:if test="${fn:length(actionBean.senaraiKeempunyaan[0].senaraiWaris) > 0}">
                <div class="content" align="center">
                    <br>
                    Pemegang Amanah Bagi Pihak
                    <display:table name="${actionBean.senaraiKeempunyaan}" id="linePA" class="tablecloth" >
                        <display:column title="Bil" sortable="true">${linePA_rowNum}</display:column>
                        <display:column title="Nama">
                            <font style="text-transform:uppercase;">
                            ${linePA.senaraiWaris[linePA_rowNum-1].nama}
                            </font>
                        </display:column>
                        <display:column title="Jenis Pengenalan2222">
                            <c:if test="${linePA.senaraiWaris[linePA_rowNum-1].jenisPengenalan eq null}"> - </c:if>
                            <c:if test="${linePA.senaraiWaris[linePA_rowNum-1].jenisPengenalan ne null}">
                                <font style="text-transform:uppercase;">
                                ${linePA.senaraiWaris[linePA_rowNum-1].jenisPengenalan.nama}
                                </font>
                            </c:if>
                        </display:column>
                        <display:column  title="Nombor Pengenalan">
                            <c:if test="${linePA.senaraiWaris[linePA_rowNum-1].noPengenalan eq null}"> - </c:if>
                            <c:if test="${linePA.senaraiWaris[linePA_rowNum-1].noPengenalan ne null}">
                                <font style="text-transform:uppercase;">
                                ${linePA.senaraiWaris[linePA_rowNum-1].noPengenalan}
                                </font>
                            </c:if>
                        </display:column>
                    </display:table>
                </div>
            </c:if>

            <c:if test="${fn:length(actionBean.pengarahList) > 0}">
                <div class="content" align="center">
                    <br>
                    Maklumat Ahli Lembaga Pengarah
                    <display:table name="${actionBean.pengarahList}" id="line2" class="tablecloth" >
                        <display:column title="Bil" sortable="true">${line2_rowNum}</display:column>
                        <display:column property="nama" title="Nama"/>
                        <display:column property="jenisPengenalan.nama" title="Jenis Pengenalan"/>
                        <display:column property="noPengenalan" title="Nombor Pengenalan"/>
                        <display:column property="warganegara.nama" title="Warganegara"/>
                    </display:table>
                </div>
            </c:if>

            <c:if test="${fn:length(actionBean.senaraiTanahLain) > 0}">
                <div class="content" align="center">
                    <br>
                    Maklumat lain-lain tanah yang dimiliki
                    <display:table name="${actionBean.senaraiTanahLain}" id="lineTanah" class="tablecloth" >
                        <display:column title="Bil" sortable="true">${lineTanah_rowNum}</display:column>
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                        <c:if test="${lineTanah.hakmilik.noLot eq 'Tiada' || lineTanah.hakmilik.noLot  eq 'X'}">
                            <display:column property="hakmilik.noLot" title="Nombor Lot/PT" style="vertical-align:top"/>
                        </c:if>
                        <c:if test="${lineTanah.hakmilik.noLot ne 'Tiada' && lineTanah.hakmilik.noLot  ne 'X'}">
                            <display:column title="Nombor Lot/PT" style="vertical-align:top"><fmt:parseNumber value="${lineTanah.hakmilik.noLot}"/></display:column>
                        </c:if>
                        <display:column title="Luas"  style="vertical-align:top"><fmt:formatNumber pattern="#,##0.0000" value="${lineTanah.hakmilik.luas}"/>&nbsp;<font style="text-transform:uppercase;">${lineTanah.hakmilik.kodUnitLuas.nama}</font></display:column>
                        <display:column title="Daerah" class="daerah" style="vertical-align:top"><font style="text-transform:uppercase;">${lineTanah.hakmilik.daerah.nama}</font></display:column>
                        <display:column title="Bandar/Pekan/Mukim" style="vertical-align:top"><font style="text-transform:uppercase;">${lineTanah.hakmilik.bandarPekanMukim.nama}</font></display:column>
                        <display:column title="Syer Dimiliki" >
                            <div align="center">
                                ${lineTanah.syerPembilang}/${lineTanah.syerPenyebut}
                            </div>
                        </display:column>
                    </display:table>
                </div>
            </c:if>

            <c:if test="${penerima}">

                <c:if test="${fn:length(actionBean.cawanganList) > 0}">
                    <div class="content" align="center">
                        <br>
                        Maklumat Cawangan
                        <display:table name="${actionBean.cawanganList}" id="line" class="tablecloth" >
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column property="namaCawangan" title="Nama"/>
                            <display:column title="Alamat" class="alamat">
                                ${line.suratAlamat1}<c:if test="${line.suratAlamat2 ne null}">,</c:if>
                                ${line.suratAlamat2}<c:if test="${line.suratAlamat3 ne null}">,</c:if>
                                ${line.suratAlamat3}<c:if test="${line.suratAlamat4 ne null}">,</c:if>
                                ${line.suratAlamat4}<c:if test="${line.suratPoskod ne null}">,</c:if>
                                ${line.suratPoskod}<c:if test="${line.suratNegeri.kod ne null}">,</c:if>
                                <font style="text-transform:uppercase;">${line.suratNegeri.nama}</font>
                            </display:column>
                        </display:table>
                    </div>
                </c:if>
                <c:if test="${fn:length(actionBean.permohonanPihak.senaraiHubungan) > 0}">
                    <div class="content" align="center">
                        <br>
                        <c:if test="${actionBean.permohonanPihak.jenis.kod eq 'PA'}">
                            Maklumat Penerima Amanah
                        </c:if>
                        <c:if test="${actionBean.permohonanPihak.jenis.kod eq 'PP'}">
                            Maklumat Penerima Tadbir
                        </c:if>
                        <c:if test="${actionBean.permohonanPihak.jenis.kod eq 'WS'}">
                            Maklumat Penerima Wasi
                        </c:if>

                        <display:table name="${actionBean.permohonanPihak.senaraiHubungan}" id="line" class="tablecloth" >
                            <display:column property="nama" title="Nama"/>
                            <display:column property="jenisPengenalan.nama" title="Jenis Pengenalan33333"/>
                            <display:column property="noPengenalan" title="Nombor Pengenalan"/>
                            <display:column property="wargaNegara.nama" title="Warganegara"/>
                            <display:column title="Alamat" class="alamat">
                                <font style="text-transform:uppercase;">
                                ${line.alamat1}<c:if test="${line.alamat2 ne null}">,</c:if>
                                ${line.alamat2}<c:if test="${line.alamat3 ne null}">,</c:if>
                                ${line.alamat3}<c:if test="${line.alamat4 ne null}">,</c:if>
                                ${line.alamat4}<c:if test="${line.poskod ne null}">,</c:if>
                                ${line.poskod}<c:if test="${line.negeri ne null}">,</c:if>
                                ${line.negeri.nama}<c:if test="${line.negara ne null}">,</c:if>
                                ${line.negara.nama}
                                </font>
                            </display:column>
                            <display:column title="Syer">
                                <div align="center"> ${line.syerPembilang}/${line.syerPenyebut}</div>
                            </display:column>
                        </display:table>
                    </div>
                </c:if>
            </c:if>
            <br/>
            <p align="center">
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
            <br/>
        </fieldset>
    </div>
</s:form>
