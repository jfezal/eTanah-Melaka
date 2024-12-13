<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:100px;
        margin-right:0.5em;
        text-align:right;
        width:15em;
        vertical-align:top;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        width:40em;
    }
    #tdDisplay2 {
        color:black;
        font-size:13px;
        font-weight:normal;
        width:50em;
    }
    #tdDisplay3 {
        color:black;
        font-size:13px;
        font-weight:normal;
        width:1em;
    }
    #tdLabel2 {
        color:#003194;
        display:block;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;

        text-align:right;
        width:1em;
        vertical-align:top;
    }
</style>
<s:form beanclass="etanah.view.stripes.common.MaklumatPermohonanActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Permohonan</legend>
            <table border="0">
                <tr><td>&nbsp;</td></tr>
                <tr>
                    <td id="tdLabel">Permohonan :&nbsp;</td>
                    <td id="tdDisplay">${actionBean.permohonanSebelum.kodUrusan.nama}&nbsp;</td>
                </tr>
                <tr>
                    <td id="tdLabel">ID Permohonan :&nbsp;</td>
                    <td id="tdDisplay">${actionBean.permohonanSebelum.idPermohonan}&nbsp;</td>

                </tr>
                <tr>
                    <td id="tdLabel">Status : </td>
                    <c:choose>
                        <c:when test="${actionBean.permohonanSebelum.status eq 'SL'}">
                            <td id="tdDisplay">Selesai </td>
                        </c:when>             
                        <c:when test="${actionBean.permohonanSebelum.status eq 'AK' && actionBean.permohonanSebelum.keputusan.kod eq 'G'}">
                            <td id="tdDisplay">Aktif/Gantung </td>
                        </c:when>        
                        <c:when test="${actionBean.permohonanSebelum.status eq 'AK'}">
                            <td id="tdDisplay">Aktif </td>
                        </c:when>
                        <c:when test="${actionBean.permohonanSebelum.status eq 'TA'}">
                            <td id="tdDisplay">Tunggu Untuk Diambil </td>
                        </c:when>
                        <c:otherwise>
                            <td id="tdDisplay">${actionBean.permohonanSebelum.status} &nbsp;</td>
                        </c:otherwise>
                    </c:choose>
                </tr>
                <c:if test="${actionBean.permohonanSebelum.status eq 'SL'}">

                    <tr>
                        <td id="tdLabel">Keputusan :&nbsp;</td>
                        <td id="tdDisplay">${actionBean.permohonanSebelum.keputusan.nama}&nbsp;</td>
                    </tr>
                    <tr>
                        <td id="tdLabel">Tarikh Keputusan :&nbsp;</td>
                        <td id="tdDisplay">
                            <c:if test="${actionBean.permohonanSebelum.tarikhKeputusan ne null}">
                                <fmt:formatDate value="${actionBean.permohonanSebelum.tarikhKeputusan}" pattern="dd/MM/yyyy hh:mm:ss"/>&nbsp;
                            </c:if>
                            <c:if test="${actionBean.permohonanSebelum.tarikhKeputusan eq null}">
                                -
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td id="tdLabel">Keputusan Oleh :&nbsp;</td>
                        <td id="tdDisplay">${fn:toUpperCase(actionBean.permohonanSebelum.keputusanOleh.nama)}&nbsp;</td>
                    </tr>
                </c:if>
            </table>
            </br>
        </fieldset>
    </div>
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Penyerah</legend>
            <table border="0">
                <tr><td>&nbsp;</td></tr>
                <tr>
                    <td id="tdLabel">Nama Penyerah / Pemohon:&nbsp;</td>
                    <td id="tdDisplay">${actionBean.permohonanSebelum.penyerahNama}&nbsp;</td>
                </tr>
                <tr>
                    <td id="tdLabel">Nombor Rujukan Penyerah :&nbsp;</td>
                    <c:if test="${actionBean.permohonanSebelum.penyerahNoRujukan ne null}">
                        <td id="tdDisplay">${actionBean.permohonanSebelum.penyerahNoRujukan}&nbsp;</td>
                    </c:if>
                    <c:if test="${actionBean.permohonanSebelum.penyerahNoRujukan eq null}">
                        <td id="tdDisplay"> - </td>
                    </c:if>
                </tr>
                <tr>
                    <td id="tdLabel">Alamat Penyerah :&nbsp;</td>
                    <td id="tdDisplay">${actionBean.permohonanSebelum.penyerahAlamat1}&nbsp;</td>
                </tr>
                <c:if test="${actionBean.permohonanSebelum.penyerahAlamat2 ne null}">
                    <tr>
                        <td id="tdLabel">&nbsp;</td>
                        <td id="tdDisplay">${actionBean.permohonanSebelum.penyerahAlamat2}&nbsp;</td>
                    </tr>
                </c:if>
                <c:if test="${actionBean.permohonanSebelum.penyerahAlamat3 ne null}">
                    <tr>
                        <td id="tdLabel">&nbsp;</td>
                        <td id="tdDisplay">${actionBean.permohonanSebelum.penyerahAlamat3}&nbsp;</td>
                    </tr>
                </c:if>
                <tr>
                    <td id="tdLabel">Bandar :&nbsp;</td>
                    <c:if test="${actionBean.permohonanSebelum.penyerahAlamat4 ne null}">
                        <td id="tdDisplay">${actionBean.permohonanSebelum.penyerahAlamat4}&nbsp;</td>
                    </c:if>
                    <c:if test="${actionBean.permohonanSebelum.penyerahAlamat4 eq null}">
                        <td id="tdDisplay"> - </td>
                    </c:if>
                </tr>
                <tr>
                    <td id="tdLabel">Poskod :&nbsp;</td>
                    <c:if test="${actionBean.permohonanSebelum.penyerahPoskod ne null}">
                        <td id="tdDisplay">${actionBean.permohonanSebelum.penyerahPoskod}&nbsp;</td>
                    </c:if>
                    <c:if test="${actionBean.permohonanSebelum.penyerahPoskod eq null}">
                        <td id="tdDisplay"> - </td>
                    </c:if>
                </tr>
                <tr>
                    <td id="tdLabel">Negeri :&nbsp;</td>
                    <c:if test="${actionBean.permohonanSebelum.penyerahNegeri.nama ne null}">
                        <td id="tdDisplay">${actionBean.permohonanSebelum.penyerahNegeri.nama}&nbsp;</td>
                    </c:if>
                    <c:if test="${actionBean.permohonanSebelum.penyerahNegeri.nama eq null}">
                        <td id="tdDisplay"> - </td>
                    </c:if>
                </tr>
            </table>      
            <br>
        </fieldset>
    </div>
    <br>
    <c:if test="${actionBean.permohonanSebelum.kodUrusan.kod eq 'PLT'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Mahkamah</legend>
                <p>
                    <label>Perintah Larangan Atas Tanah bagi tempoh selama :</label>
                    ${actionBean.hu.tempohTahun}&nbsp;Tahun,&nbsp;
                    ${actionBean.hu.tempohBulan}&nbsp;Bulan,&nbsp;          
                    ${actionBean.hu.tempohHari}&nbsp;Hari&nbsp;
                </p>
                <p>
                    <label>Tarikh Mula :</label>
                    <c:choose>
                        <c:when test="${actionBean.hu.tarikhSidang ne null}">              
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.hu.tarikhSidang}"/>&nbsp;
                        </c:when>
                        <c:otherwise>
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonanRujukanLuar.tarikhKuatkuasa}"/>&nbsp;
                        </c:otherwise>
                    </c:choose>
                </p>
                <p>
                    <label>Tarikh Tamat :</label>
                    <c:choose>
                        <c:when test="${actionBean.hu.tarikhTamat ne null}">              
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.hu.tarikhTamat}"/>&nbsp;
                        </c:when>
                        <c:otherwise>
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonanRujukanLuar.tarikhTamat}"/>&nbsp;
                        </c:otherwise>
                    </c:choose>
                </p>
                <p>
                    <label>No. Perintah Mahkamah :</label>          
                    <c:choose>
                        <c:when test="${actionBean.hu.noSidang ne null}">              
                            ${actionBean.hu.noSidang}&nbsp;
                        </c:when>
                        <c:otherwise>
                            ${actionBean.hu.noSidang}&nbsp;
                        </c:otherwise>
                    </c:choose>
                </p>
                <p>
                    <label>Bertarikh :</label>
                    <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.hu.tarikhSidang}"/>&nbsp;
                </p>
                <br>
            </fieldset >
        </div>
        <br>
    </c:if>
    <c:if test="${actionBean.permohonanSebelum.kodUrusan.kod eq 'PLK'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Mahkamah</legend> 
                <p>
                    <label>Perintah Larangan Atas Kepentingan ke atas :</label>
                    ${actionBean.husblm.idPerserahan} - ${actionBean.husblm.kodUrusan.nama}&nbsp;
                </p>
                <p>
                    <label>selama :</label>
                    ${actionBean.hu.tempohTahun}&nbsp;Tahun,
                    ${actionBean.hu.tempohBulan}&nbsp; Bulan,
                    ${actionBean.hu.tempohHari}&nbsp;Hari 
                </p>
                <p>
                    <label>Tarikh Mula :</label>
                    <c:choose>
                        <c:when test="${actionBean.hu.tarikhSidang ne null}">              
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.hu.tarikhSidang}"/>&nbsp;
                        </c:when>
                        <c:otherwise>
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonanRujukanLuar.tarikhKuatkuasa}"/>&nbsp;
                        </c:otherwise>
                    </c:choose>
                </p>
                <p>
                    <label>Tarikh Tamat :</label>
                    <c:choose>
                        <c:when test="${actionBean.hu.tarikhTamat ne null}">              
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.hu.tarikhTamat}"/>&nbsp;
                        </c:when>
                        <c:otherwise>
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonanRujukanLuar.tarikhTamat}"/>&nbsp;
                        </c:otherwise>
                    </c:choose>
                </p>
                <p>
                    <label>No. Perintah Mahkamah :</label>
                    ${actionBean.hu.noSidang}&nbsp;
                </p>
                <p>
                    <label>Bertarikh :</label>
                    <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.hu.tarikhSidang}"/>&nbsp;
                </p>
                <br>
            </fieldset >
        </div>
    </c:if>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pihak Berkepentingan</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hpkList1}"  cellpadding="0" cellspacing="0"
                               requestURI="/common/maklumat_hakmilik_permohonan" id="newLine">
                    <display:column title="No">${newLine_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                    <display:column property="nama" title="Nama" style="width:50%"/>  
                    <display:column title="Syer"> 
                        <c:choose>
                            <c:when test="${newLine.jenis.kod eq 'PM' || newLine.jenis.kod eq 'PA' || newLine.jenis.kod eq 'PP' || newLine.jenis.kod eq 'PK' 
                                            || newLine.jenis.kod eq 'RP' || newLine.jenis.kod eq 'WPA' || newLine.jenis.kod eq 'KL' || newLine.jenis.kod eq 'WKL'
                                            || newLine.jenis.kod eq 'WS' || newLine.jenis.kod eq 'WAR' || newLine.jenis.kod eq 'JG' || newLine.jenis.kod eq 'CP'
                                            || newLine.jenis.kod eq 'JA' || newLine.jenis.kod eq 'PUH' || newLine.jenis.kod eq 'PAS' || newLine.jenis.kod eq 'ROS'
                                            || newLine.jenis.kod eq 'JK' || newLine.jenis.kod eq 'PLK'}">
                                <c:if test="${newLine.syerPembilang ne null && newLine.syerPenyebut ne null}">
                                    ${newLine.syerPembilang}/${newLine.syerPenyebut}
                                </c:if>
                            </c:when>     
                            <c:otherwise>
                                &nbsp;
                            </c:otherwise>
                        </c:choose>
                    </display:column>
                    <display:column title="Jenis Pihak">
                        ${newLine.jenis.nama}
                        <c:if test="${newLine.jenis.kod eq 'PA'}">
                            <br/>
                            bagi pihak
                            <br/>
                            <c:forEach items="${newLine.senaraiWaris}" var="waris">
                                ${waris.nama}<br/>
                            </c:forEach>
                        </c:if>
                    </display:column>           
                </display:table>
            </div>
        </fieldset >
    </div>
    <br>
    
    <c:if  test="${actionBean.permohonanList ne null}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Urusan Berangkai</legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.permohonanList}"  cellpadding="0" cellspacing="0"
                                   requestURI="/common/maklumat_hakmilik_permohonan" id="newLine">
                        <display:column title="No">${newLine_rowNum}</display:column>
                        <display:column property="idPermohonan" title="ID Permohonan"/>         
                        <display:column property="kodUrusan.nama" title="Nama Urusan"/>         
                        <display:column property="tarikhKeputusan" title="Tarikh Daftar"/>         
                        <display:column property="kumpulanNo" title="Turutan Permohonan"/>         
                    </display:table>
                </div>
            </fieldset >
        </div>
        <br>
    </c:if>

    <!-- Start: Maklumat Permohonan Atas Hubungan -->
    <c:if  test="${actionBean.permohonanSebelum.kodUrusan.kod eq 'PHD' 
                   || actionBean.permohonanSebelum.kodUrusan.kod eq 'PJLT'
                   || actionBean.permohonanSebelum.kodUrusan.kod eq 'TENL'
                   || actionBean.permohonanSebelum.kodUrusan.kod eq 'PMSE'
                   || actionBean.permohonanSebelum.kodUrusan.kod eq 'KVATB'
                   || actionBean.permohonanSebelum.kodUrusan.kod eq 'GDPJL'}">
           <div class="subtitle">
               <fieldset class="aras1">

                   <c:if test="${actionBean.permohonanHubunganList ne null}">
                       <legend>Maklumat Perserahan Terlibat</legend>
                       <table border="0">
                           <tr><td>&nbsp;</td></tr>
                           <c:forEach items="${actionBean.permohonanHubunganList}" var="item">

                               <tr>
                                   <td id="tdLabel">Urusan :&nbsp;</td>
                                   <td id="tdDisplay">${item.permohonanSasaran.kodUrusan.nama}&nbsp;</td>
                               </tr>
                               <tr>
                                   <td id="tdLabel">No. Perserahan :&nbsp;</td>
                                   <td id="tdDisplay">${item.permohonanSasaran.idPermohonan}&nbsp;</td>
                               </tr>

                           </c:forEach>
                           <tr><td>&nbsp;</td></tr>
                       </table>      
                   </c:if>

               </fieldset>
           </div>
    </c:if>
    <!-- End:Maklumat Permohonan Atas Hubungan -->
    <!--Maklumat Pajakan :Start-->
    <c:if test="${actionBean.permohonanSebelum.kodUrusan.kod eq 'PJT'
                  || actionBean.permohonanSebelum.kodUrusan.kod eq 'PJLT'
                  || actionBean.permohonanSebelum.kodUrusan.kod eq 'PJKT'
                  || actionBean.permohonanSebelum.kodUrusan.kod eq 'PJBT'}">
          <br><div class="subtitle">
              <fieldset class="aras1">
                  <legend>Maklumat Pajakan</legend>
                  <br>
                  <table border="0">
                      <tr>
                          <td id="tdLabel">Tarikh Mula Pajak :&nbsp;</td>
                          <c:choose>
                              <c:when test="${actionBean.permohonanRujukanLuar.tarikhKuatkuasa ne null}">
                                  <td id="tdDisplay"><fmt:formatDate value="${actionBean.permohonanRujukanLuar.tarikhKuatkuasa}" pattern="dd/MM/yyyy"/>&nbsp;</td>
                              </c:when>
                              <c:otherwise>
                                  <td id="tdDisplay"><fmt:formatDate value="${actionBean.hu.tarikhDaftar}" pattern="dd/MM/yyyy"/>&nbsp;</td>
                              </c:otherwise>
                          </c:choose>
                      </tr>
                      <tr>
                          <td id="tdLabel">Tarikh Akhir Pajak :&nbsp;</td>
                          <c:choose>
                              <c:when test="${actionBean.permohonanRujukanLuar.tarikhTamat ne null}">
                                  <td id="tdDisplay"><fmt:formatDate value="${actionBean.permohonanRujukanLuar.tarikhTamat}" pattern="dd/MM/yyyy"/>&nbsp;</td>
                              </c:when>
                              <c:otherwise>
                                  <td id="tdDisplay"><fmt:formatDate value="${actionBean.hu.tarikhTamat}" pattern="dd/MM/yyyy"/>&nbsp;</td>
                              </c:otherwise>
                          </c:choose>
                      </tr>
                      <tr>
                          <td id="tdLabel">Tempoh Pajak :&nbsp;</td>
                          <td id="tdDisplay">
                              <c:if test="${actionBean.hu.tempohTahun ne 0}">
                                  ${actionBean.hu.tempohTahun} Tahun&nbsp;&nbsp;&nbsp;                    
                              </c:if>
                              <c:if test="${actionBean.hu.tempohBulan ne 0}">
                                  ${actionBean.hu.tempohBulan} Bulan&nbsp;&nbsp;&nbsp;                   
                              </c:if>
                              <c:if test="${actionBean.hu.tempohHari ne 0}">
                                  ${actionBean.hu.tempohHari} Hari&nbsp;                    
                              </c:if> 
                          </td>
                      </tr>
                  </table>
                  </br>
              </fieldset>
          </div>
          <br>
    </c:if>

    <!--Maklumat Pajakan : End-->
    <!--  Start:Maklumat Luas/Cukai-->
    <c:if  test="${actionBean.permohonanSebelum.kodUrusan.kod eq 'HLTE' 
                   || actionBean.permohonanSebelum.kodUrusan.kod eq 'PJBT'
                   || actionBean.permohonanSebelum.kodUrusan.kod eq 'PCT'}">
           <div class="subtitle">
               <fieldset class="aras1">
                   <c:choose>
                       <c:when test="${actionBean.hu.luasTerlibat ne null && (actionBean.hu.cukaiBaru eq null && actionBean.hu.cukaiLama eq null)}">
                           <legend>Maklumat Luas</legend>
                           <table border="0">
                               <tr><td>&nbsp;</td></tr>
                               <tr>
                                   <td id="tdLabel">Luas :&nbsp;</td>
                                   <td id="tdDisplay">
                                       <fmt:formatNumber type="number" pattern="###,###.0000" value="${actionBean.hu.luasTerlibat}" />
                                       &nbsp;Meter Persegi
                                   </td>
                               </tr>
                               <tr><td>&nbsp;</td></tr>
                           </table>
                       </c:when>
                       <c:when test="${(actionBean.hu.cukaiBaru ne null 
                                       || actionBean.hu.cukaiLama ne null)
                                       && actionBean.hu.luasTerlibat eq null}">
                               <legend>Maklumat Cukai</legend>
                               <table>
                                   <tr><td>&nbsp;</td></tr>
                                   <tr> 
                                       <c:choose>
                                           <c:when test="${actionBean.hu.cukaiBaru ne null}">
                                               <td id="tdLabel">Cukai:&nbsp;</td>
                                               <td id="tdDisplay">RM 
                                                   <fmt:formatNumber type="number" pattern="###,###.00" value="${actionBean.hu.cukaiBaru}" />&nbsp;
                                               </td>
                                           </c:when>
                                           <c:otherwise>
                                               <td id="tdLabel">Cukai :&nbsp;</td>
                                               <td id="tdDisplay">RM 
                                                   <fmt:formatNumber type="number" pattern="###,###.00" value="${actionBean.hu.cukaiLama}" />&nbsp;
                                               </td>
                                           </c:otherwise>
                                       </c:choose>
                                   </tr>
                                   <tr><td>&nbsp;</td></tr>
                               </table>
                       </c:when>
                       <c:when test="${(actionBean.hu.cukaiBaru ne null 
                                       || actionBean.hu.cukaiLama ne null)
                                       && actionBean.hu.luasTerlibat ne null}">
                               <legend>Maklumat Luas/Cukai</legend>
                               <table border="0">
                                   <tr><td>&nbsp;</td></tr>
                                   <tr>
                                       <td id="tdLabel">Luas :&nbsp;</td>
                                       <td id="tdDisplay">
                                           <fmt:formatNumber type="number" pattern="###,###.0000" value="${actionBean.hu.luasTerlibat}" />
                                           &nbsp;Meter Persegi
                                       </td>
                                   </tr>
                                   <tr> 
                                       <c:choose>
                                           <c:when test="${actionBean.hu.cukaiBaru ne null}">
                                               <td id="tdLabel">Cukai:&nbsp;</td>
                                               <td id="tdDisplay">RM 
                                                   <fmt:formatNumber type="number" pattern="###,###.00" value="${actionBean.hu.cukaiBaru}" />&nbsp;
                                               </td>
                                           </c:when>
                                           <c:otherwise>
                                               <td id="tdLabel">Cukai :&nbsp;</td>
                                               <td id="tdDisplay">RM 
                                                   <fmt:formatNumber type="number" pattern="###,###.00" value="${actionBean.hu.cukaiLama}" />&nbsp;
                                               </td>
                                           </c:otherwise>
                                       </c:choose>
                                   </tr>
                                   <tr><td>&nbsp;</td></tr>
                               </table>      
                       </c:when>
                   </c:choose>
               </fieldset>
           </div>
    </c:if>
    <!--urusan ABTKB tarik data guna table mohon_hakmilik-->
    <c:if  test="${actionBean.permohonanSebelum.kodUrusan.kod eq 'ABTKB'}">
        <c:if test="${actionBean.hp.luasTerlibat ne null ||actionBean.hp.cukaiBaru ne null }">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Maklumat Luas/Cukai</legend>
                    <table border="0">
                        <tr><td>&nbsp;</td></tr>
                        <c:if test="${actionBean.hp.luasTerlibat ne null}">
                            <tr>
                                <td id="tdLabel">Luas :&nbsp;</td>
                                <td id="tdDisplay">
                                    <fmt:formatNumber type="number" pattern="###,###.0000" value="${actionBean.hp.luasTerlibat}" />
                                    &nbsp;${actionBean.hp.kodUnitLuas.nama}
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${actionBean.hp.cukaiBaru ne null}">
                            <tr> 
                                <td id="tdLabel">Cukai:&nbsp;</td>
                                <td id="tdDisplay">RM 
                                    <fmt:formatNumber type="number" pattern="###,###.00" value="${actionBean.hp.cukaiBaru}" />&nbsp;
                                </td>
                            </tr>
                        </c:if>
                        <tr><td>&nbsp;</td></tr>
                    </table>      
                </fieldset>
            </div>
        </c:if>
    </c:if>
    <!--End: Maklumat Luas/Cukai-->

    <!--Start :Paparkan Pemberi dan Penerima-->  
    <!--kalau tak kua penerima and pemberi, betol kan le code ni.. maleh lak nak test sume condition --> 
    <c:if test="${fn:length(actionBean.pemohonList) > 0 || fn:length(actionBean.permohonanPihakList) > 0 }">
        <c:set var="count" value="0"/>
        <div class="subtitle">
            <fieldset class="aras1">

                <!--Start:Title Maklumat Urusan -->  
                <c:choose>
                    <c:when test="${actionBean.permohonanSebelum.kodUrusan.kod eq 'PHD'}">
                        <legend>Maklumat Perletakhakan Kepada Danaharta</legend>
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.permohonanSebelum.kodUrusan.kod,'KV')}">
                        <legend>Maklumat Kaveat</legend>
                    </c:when>
                    <c:otherwise>
                        <legend>Maklumat Pemberi dan Penerima</legend>
                    </c:otherwise>
                </c:choose>
                <!--End:Title Maklumat Urusan -->
                <!--Daripada-->
                <c:if test="${fn:length(actionBean.pemohonList) > 0 }">
                    <br>
                    <table border="0">
                        <tr>
                            <c:choose>
                                <c:when test="${fn:startsWith(actionBean.permohonanSebelum.kodUrusan.kod,'KV')}">
                                    <td id="tdLabel">Ke atas bahagian :&nbsp;</td>
                                </c:when>
                                <c:otherwise>
                                    <td id="tdLabel">Daripada :&nbsp;</td>
                                </c:otherwise>
                            </c:choose>
                            <td>
                                <display:table class="tablecloth" name="${actionBean.pemohonList}"  cellpadding="0" cellspacing="0" id="item" requestURI="/common/maklumat_hakmilik_permohonan">
                                    <display:column title="No">${item_rowNum}</display:column>
                                    <display:column property="nama" title="Nama"/>
                                    <display:column title="No. Pengenalan/No. Syarikat">
                                        <c:if test="${item.jenisPengenalan.kod ne '0'}">${item.jenisPengenalan.nama}: ${item.noPengenalan}</c:if>
                                    </display:column>
                                    <display:column title="Alamat">
                                        <c:if test="${item.alamat.alamat1 ne null}">
                                            ${item.alamat.alamat1}
                                        </c:if>
                                        <c:if test="${item.alamat.alamat2 ne null}">
                                            ,
                                        </c:if>${item.alamat.alamat2}
                                        <c:if test="${item.alamat.alamat3 ne null}">
                                            ,<br/> ${item.alamat.alamat3} 
                                        </c:if>                      
                                        <c:if test="${item.alamat.alamat4 ne null}">
                                            ,
                                        </c:if>${item.alamat.alamat4}                          
                                        <c:if test="${item.alamat.poskod ne null}">
                                            ,
                                        </c:if>${item.alamat.poskod}                         
                                        <c:if test="${item.alamat.negeri.nama ne null}">
                                            ,
                                        </c:if>${item.alamat.negeri.nama}
                                    </display:column> 
                                    <display:column title="Syer"><c:if test="${item.syerPembilang ne null && item.syerPenyebut ne null}">
                                            ${item.syerPembilang}/${item.syerPenyebut}
                                        </c:if></display:column> 
                                </display:table>
                            </td>
                    </table>
                </c:if>
                <br>
                <!--Kepada-->
                <c:if test="${fn:length(actionBean.permohonanPihakList) > 0 }">
                    <table border="0">
                        <tr>
                            <c:choose>
                                <c:when test="${fn:startsWith(actionBean.permohonanSebelum.kodUrusan.kod,'KV')}">
                                    <td id="tdLabel">Oleh :&nbsp;</td>
                                </c:when>
                                <c:otherwise>
                                    <td id="tdLabel">Kepada :&nbsp;</td>
                                </c:otherwise>
                            </c:choose>
                            <td>
                                <display:table class="tablecloth" name="${actionBean.permohonanPihakList}"  cellpadding="0" cellspacing="0" id="item" requestURI="/common/maklumat_hakmilik_permohonan">
                                    <display:column title="No">${item_rowNum}</display:column>
                                    <display:column property="nama" title="Nama"/>
                                    <display:column title="No. Pengenalan/No. Syarikat">
                                        <c:if test="${item.jenisPengenalan.kod ne '0'}">${item.jenisPengenalan.nama}: ${item.noPengenalan}</c:if>
                                    </display:column>
                                    <display:column title="Alamat">
                                        <c:if test="${item.alamat.alamat1 ne null}">
                                            ${item.alamat.alamat1}
                                        </c:if>
                                        <c:if test="${item.alamat.alamat2 ne null}">
                                            ,
                                        </c:if>${item.alamat.alamat2}
                                        <c:if test="${item.alamat.alamat3 ne null}">
                                            ,<br/> ${item.alamat.alamat3} 
                                        </c:if>                      
                                        <c:if test="${item.alamat.alamat4 ne null}">
                                            ,
                                        </c:if>${item.alamat.alamat4}                          
                                        <c:if test="${item.alamat.poskod ne null}">
                                            ,
                                        </c:if>${item.alamat.poskod}                         
                                        <c:if test="${item.alamat.negeri.nama ne null}">
                                            ,
                                        </c:if>${item.alamat.negeri.nama}
                                    </display:column> 
                                    <display:column title="Syer"><c:if test="${item.syerPembilang ne null && item.syerPenyebut ne null}">
                                            ${item.syerPembilang}/${item.syerPenyebut}
                                        </c:if></display:column> 
                                </display:table>
                            </td>
                    </table>
                </c:if>
                <br>
            </fieldset>
        </div>     
    </c:if>
    <!--end:Paparkan Pemberi dan Penerima-->
    <!--Urusan GDPJL-->
    <c:if test="${actionBean.permohonanSebelum.kodUrusan.kod eq 'GDPJL' || actionBean.permohonanSebelum.kodUrusan.kod eq 'PJKT' }">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Pihak Terlibat</legend>
                <table border="0">
                    <tr>
                        <td id="tdLabel">Oleh :&nbsp;</td>

                        <td>
                            <display:table class="tablecloth" name="${actionBean.permohonanPihakList}"  cellpadding="0" cellspacing="0" id="item" requestURI="/common/maklumat_hakmilik_permohonan">
                                <display:column title="No">${item_rowNum}</display:column>
                                <display:column property="nama" title="Nama"/>
                                <display:column title="No. Pengenalan/No. Syarikat">
                                    <c:if test="${item.jenisPengenalan.kod ne '0'}">${item.jenisPengenalan.nama}: ${item.noPengenalan}</c:if>
                                </display:column>
                                <display:column title="Alamat">
                                    <c:if test="${item.alamat.alamat1 ne null}">
                                        ${item.alamat.alamat1}
                                    </c:if>
                                    <c:if test="${item.alamat.alamat2 ne null}">
                                        ,
                                    </c:if>${item.alamat.alamat2}
                                    <c:if test="${item.alamat.alamat3 ne null}">
                                        ,<br/> ${item.alamat.alamat3} 
                                    </c:if>                      
                                    <c:if test="${item.alamat.alamat4 ne null}">
                                        ,
                                    </c:if>${item.alamat.alamat4}                          
                                    <c:if test="${item.alamat.poskod ne null}">
                                        ,
                                    </c:if>${item.alamat.poskod}                         
                                    <c:if test="${item.alamat.negeri.nama ne null}">
                                        ,
                                    </c:if>${item.alamat.negeri.nama}
                                </display:column> 
                                <display:column title="Syer"><c:if test="${item.syerPembilang ne null && item.syerPenyebut ne null}">
                                        ${item.syerPembilang}/${item.syerPenyebut}
                                    </c:if></display:column> 
                            </display:table>
                        </td>
                </table>
                <br>
            </fieldset>
        </div>
    </c:if>
    <!--End GDPJL-->

    <!-- PEMBERI PENERIMA for Wakil_Kuasa -->
    <c:if test="${actionBean.permohonanSebelum.kodUrusan.kod eq 'SW' || actionBean.permohonanSebelum.kodUrusan.kod eq 'SA' }">
        <c:set var="count" value="0"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Pemberi dan Penerima</legend>
                <br>
                <table border="0">
                    <tr>
                        <td id="tdLabel">Pemberi :&nbsp;</td>            
                        <td>
                            <display:table class="tablecloth" name="${actionBean.wkPberi}"  
                                           cellpadding="0" cellspacing="0" id="item" 
                                           requestURI="/common/maklumat_hakmilik_permohonan">
                                <display:column title="No">${item_rowNum}</display:column>
                                <display:column property="pihak.nama" title="Nama"/>
                                <display:column title="No. Pengenalan">
                                    <c:if test="${item.pihak.jenisPengenalan.kod ne null}">
                                        ${item.pihak.jenisPengenalan.nama}&nbsp;: ${item.pihak.noPengenalan}
                                    </c:if>
                                </display:column>
                                <display:column title="Alamat">
                                    <c:if test="${item.pihak.alamat1 ne null}">
                                        ${item.pihak.alamat1}
                                    </c:if>
                                    <c:if test="${item.pihak.alamat2 ne null}">
                                        ,
                                    </c:if>${item.pihak.alamat2}
                                    <c:if test="${item.pihak.alamat3 ne null}">
                                        ,<br/> ${item.pihak.alamat3} 
                                    </c:if>                      
                                    <c:if test="${item.pihak.alamat4 ne null}">
                                        ,
                                    </c:if>${item.pihak.alamat4}                          
                                    <c:if test="${item.pihak.poskod ne null}">
                                        ,
                                    </c:if>${item.pihak.poskod}                         
                                    <c:if test="${item.pihak.negeri.nama ne null}">
                                        ,
                                    </c:if>${item.pihak.negeri.nama}                
                                </display:column>
                            </display:table>
                        </td>
                </table>
                <br>
                <table border="0">
                    <tr>
                        <td id="tdLabel">Penerima :&nbsp;</td>            
                        <td>
                            <display:table class="tablecloth" name="${actionBean.wkPnerima}"  
                                           cellpadding="0" cellspacing="0" id="item" 
                                           requestURI="/common/maklumat_hakmilik_permohonan">
                                <display:column title="No">${item_rowNum}</display:column>
                                <display:column property="nama" title="Nama"/>
                                <display:column title="No. Pengenalan">
                                    <c:if test="${item.jenisPengenalan.kod ne null}">
                                        ${item.jenisPengenalan.nama}&nbsp;: ${item.noPengenalan}
                                    </c:if>
                                </display:column>
                                <display:column title="Alamat">
                                    <c:if test="${item.alamat1 ne null}">
                                        ${item.alamat.alamat1}
                                    </c:if>
                                    <c:if test="${item.alamat2 ne null}">
                                        ,
                                    </c:if>${item.alamat2}
                                    <c:if test="${item.alamat3 ne null}">
                                        ,<br/> ${item.alamat3} 
                                    </c:if>                      
                                    <c:if test="${item.alamat4 ne null}">
                                        ,
                                    </c:if>${item.alamat4}                          
                                    <c:if test="${item.poskod ne null}">
                                        ,
                                    </c:if>${item.poskod}                         
                                    <c:if test="${item.negeri.nama ne null}">
                                        ,
                                    </c:if>${item.negeri.nama}                
                                </display:column>
                            </display:table>
                        </td>
                </table>
                <br>
            </fieldset>
        </div>
    </c:if>

    <!--Start:Permohonan Atas Perserahan -->   
      <c:if  test="${actionBean.permohonanAtasPerserahanList ne null}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Permohonan keatas Urusan</legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.permohonanAtasPerserahanList}"  cellpadding="0" cellspacing="0"
                                   requestURI="/common/maklumat_permohonan" id="newLine">
                        <display:column title="No">${newLine_rowNum}</display:column>
                        <display:column property="urusan.hakmilik.idHakmilik" title="ID Hakmilik"/>  
                        <display:column property="urusan.idPerserahan" title="No. Perserahan"/>         
                        <display:column property="urusan.kodUrusan.nama" title="Urusan"/>                              
                    </display:table>
                </div>
            </fieldset >
        </div>
        <br>
    </c:if>
    
    <!--Start:Permohonan Atas Perserahan By Mohon Hakmilik-->     
    <c:if  test="${actionBean.permohonanAtasPerserahanMHList ne null}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Permohonan keatas Urusan</legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.permohonanAtasPerserahanMHList}"  cellpadding="0" cellspacing="0"
                                   requestURI="/common/maklumat_permohonan" id="newLine">
                        <display:column title="No">${newLine_rowNum}</display:column>
                        <display:column property="hakmilikPermohonan.hakmilik.idHakmilik" title="ID Hakmilik"/> 
                        <display:column property="hakmilikPermohonan.permohonan.idPermohonan" title="No. Perserahan"/>         
                        <display:column property="hakmilikPermohonan.permohonan.kodUrusan.nama" title="Urusan"/>                              
                    </display:table>
                </div>
            </fieldset >
        </div>
        <br>
    </c:if>  
    <!--End:Permohonan Atas Perserahan -->

    <!--Start:Maklumat Penangguhan Gadaian Terlibat -->
    <c:if test="${actionBean.permohonanSebelum.kodUrusan.kod eq 'GDT'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <c:if test="${actionBean.permohonanHubunganList ne null}">
                    <legend>Maklumat Penangguhan Gadaian Terlibat</legend>
                    <table border="0">
                        <tr><td>&nbsp;</td></tr>
                        <tr>    
                            <td id="tdLabel"> No. Perserahan / Urusan : </td>
                            <td id="tdDisplay">
                                <c:forEach items="${actionBean.permohonanHubunganList}" var="item">
                                    <c:if test="${item.hubunganPermohonan.kod eq 'GT'}">
                                        ${item.permohonanSasaran.idPermohonan} / ${item.permohonanSasaran.kodUrusan.nama}
                                        </br> ditangguhkan 
                                    </c:if>
                                    <c:if test="${item.hubunganPermohonan.kod eq 'GL'}">selepas </br>
                                        ${item.permohonanSasaran.idPermohonan} / ${item.permohonanSasaran.kodUrusan.nama}&nbsp;
                                        </br>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                    </table>      
                </c:if>
            </fieldset>
        </div>
        <br>
    </c:if>
    <!--End:Maklumat Penangguhan Gadaian Terlibat -->

    <c:if test="${actionBean.permohonanSebelum.kodUrusan.kod eq 'TN'}">
        <div class="content" align="center">
            <fieldset class="aras1">
                <legend>Maklumat Pertukaran</legend>
                <display:table class="tablecloth" 
                               name="${actionBean.permohonanSebelum.senaraiPihakKemaskini}"  
                               cellpadding="0" cellspacing="0" id="kkini" 
                               requestURI="/common/maklumat_hakmilik_permohonan">
                    <display:column title="No">${kkini_rowNum}</display:column>
                    <display:column title="Nama Medan" property="namaMedan" />
                    <display:column title="Nilai Lama" property="nilaiLama" />
                    <display:column title="Nilai Baru" property="nilaiBaru" />            
                </display:table>
            </fieldset>
        </div>     


    </c:if>

    <!--Start apool :Paparkan Maklumat Mohon Ruj Luar-->
    <c:if test="${actionBean.permohonanRujukanLuar.namaSidang ne null || actionBean.permohonanRujukanLuar.noFail ne null 
                  || actionBean.permohonanSebelum.kodUrusan.kodPerserahan.kod eq 'NB' }"> 
        <br>
        <div class="subtitle">
            <fieldset class="aras1">
                <!--Start:Title Maklumat Urusan -->  
                <c:choose>
                    <c:when test="${actionBean.permohonanSebelum.kodUrusan.kod eq 'IROAB'}">
                        <legend>Maklumat Warta</legend>
                    </c:when>
                    <c:otherwise>
                        <legend>Maklumat Warta/Perintah</legend>
                    </c:otherwise>
                </c:choose>        
                <table border="0">
                    <tr><td>&nbsp;</td></tr>
                    <!--maklumat perintah-->
                    <c:if test="${actionBean.permohonanRujukanLuar.namaSidang ne null}">
                        <c:choose>
                            <c:when test="${actionBean.permohonanSebelum.kodUrusan.kod eq 'IDMLB'}">
                                <tr>
                                    <td id="tdLabel">Nama Daerah :&nbsp;</td>
                                    <td id="tdDisplay">${actionBean.permohonanRujukanLuar.namaSidang}&nbsp;</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td id="tdLabel">Nama Perintah :&nbsp;</td>
                                    <td id="tdDisplay">${actionBean.permohonanRujukanLuar.namaSidang}&nbsp;</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <c:if test="${actionBean.permohonanRujukanLuar.noSidang ne null}">
                        <tr>
                            <td id="tdLabel">No. Perintah :&nbsp;</td>
                            <td id="tdDisplay">${actionBean.permohonanRujukanLuar.noSidang}&nbsp;</td>
                        </tr>
                    </c:if>
                    <c:if test="${actionBean.permohonanRujukanLuar.tarikhSidang ne null}">
                        <tr>
                            <td id="tdLabel">Tarikh Perintah :&nbsp;</td>
                            <td id="tdDisplay"><fmt:formatDate value="${actionBean.permohonanRujukanLuar.tarikhSidang}" pattern="dd/MM/yyyy"/>&nbsp;</td>
                        </tr>
                    </c:if>
                    <!--maklumat warta-->
                    <c:if test="${actionBean.permohonanRujukanLuar.noRujukan ne null}">
                        <tr>
                            <td id="tdLabel">No. Warta :&nbsp;</td>
                            <td id="tdDisplay">${actionBean.permohonanRujukanLuar.noRujukan}&nbsp;</td>
                        </tr>
                    </c:if>
                    <c:if test="${actionBean.permohonanRujukanLuar.tarikhRujukan ne null}">
                        <tr>
                            <td id="tdLabel">Tarikh Warta :&nbsp;</td>
                            <td id="tdDisplay"><fmt:formatDate value="${actionBean.permohonanRujukanLuar.tarikhRujukan}"pattern="dd/MM/yyyy"/>&nbsp;</td>
                        </tr>
                    </c:if>
                    <c:if test="${actionBean.permohonanRujukanLuar.noFail ne null}">
                        <tr>
                            <td id="tdLabel">No. Rujukan Fail :&nbsp;</td>
                            <td id="tdDisplay">${actionBean.permohonanRujukanLuar.noFail}&nbsp;</td>
                        </tr>
                    </c:if>
                    <c:if test="${actionBean.permohonanSebelum.kodUrusan.kodPerserahan.kod eq 'N'}">
                        <c:if test="${actionBean.permohonanRujukanLuar.item ne null}">
                            <c:choose>
                                <c:when test="${actionBean.permohonanSebelum.kodUrusan.kod eq 'IDMLB'}">
                                    <tr>
                                        <td id="tdLabel">Nama Mukim :&nbsp;</td>
                                        <td id="tdDisplay">${actionBean.permohonanRujukanLuar.item}&nbsp;</td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td id="tdLabel">Kawasan :&nbsp;</td>
                                        <td id="tdDisplay">${actionBean.permohonanRujukanLuar.item}&nbsp;</td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </c:if >
                    </c:if>
                    <c:if test="${actionBean.permohonanSebelum.kodUrusan.kod eq 'IRM'}">
                        <tr>
                            <td id="tdLabel">Cukai Dipinda Kepada :&nbsp;</td>
                            <td id="tdDisplay">RM <fmt:formatNumber type="number" pattern="###,###.00" value="${actionBean.hu.cukaiBaru}" />&nbsp;</td>
                        </tr>
                    </c:if> 
                    <c:if test="${actionBean.permohonanSebelum.kodUrusan.kod eq 'TSP'}">
                        <c:if test="${actionBean.permohonanRujukanLuar.tarikhSidang ne null}">
                            <tr>
                                <td id="tdLabel">Tarikh Mula :&nbsp;</td>
                                <td id="tdDisplay"><fmt:formatDate value="${actionBean.permohonanRujukanLuar.tarikhSidang}"pattern="dd/MM/yyyy"/>&nbsp;</td>
                            </tr>
                        </c:if >
                        <c:if test="${actionBean.permohonanRujukanLuar.tarikhTamat ne null}">
                            <tr>
                                <td id="tdLabel">Tarikh Tamat :&nbsp;</td>
                                <td id="tdDisplay"><fmt:formatDate value="${actionBean.permohonanRujukanLuar.tarikhTamat}"pattern="dd/MM/yyyy"/>&nbsp;</td>
                            </tr>
                        </c:if >
                        <c:if test="${actionBean.permohonanRujukanLuar.catatan ne null}">
                            <tr>
                                <td id="tdLabel">Catatan :&nbsp;</td>
                                <td id="tdDisplay">${actionBean.permohonanRujukanLuar.catatan}&nbsp;</td>
                            </tr>
                        </c:if >
                    </c:if>
                    <c:if test="${actionBean.permohonanSebelum.kodUrusan.kodPerserahan.kod eq 'NB'}">
                        <c:if test="${actionBean.permohonanRujukanLuar.catatan ne null}">
                            <tr>
                                <td id="tdLabel">Sebab Pembatalan :&nbsp;</td>
                                <td id="tdDisplay">${actionBean.permohonanRujukanLuar.catatan}&nbsp;</td>
                            </tr>
                        </c:if >   
                   </c:if>

                </table>      
                <br>
            </fieldset>
        </div>
    </c:if>
    <!--End apool  :Paparkan Maklumat Mohon Ruj Luar-->
    <p>
        <label>&nbsp;</label>
        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
    </p>
    <br>
</s:form>
