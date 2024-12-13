
<%--
    Document   : kemasukan_perincian_hakmilik
    Created on : 21 Oktober 2009, 4:07:08 PM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<body>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>


    <%--<c:if test="${actionBean.p.kodUrusan.kod eq 'HKABS'}">
      <c:set var="disabledbtn" value="disabled"/>
    </c:if>--%>
    <script type="text/javascript">

        function reload(id)
        {


            var url = '${pageContext.request.contextPath}/daftar/pembetulan_pihak?showForm&idHakmilik=' + id;
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                        $.unblockUI();
                    });






        }
        function updateSyer(idpihak, id) {
            var s1 = $('#syer1' + id).val();
            var s2 = $('#syer2' + id).val();

            if (s1 == '' || s2 == '') {
                return;
            }

            if (isNaN(s1) && isNaN(s2)) {
                return;
            }
            var url = '${pageContext.request.contextPath}/pihakBerkepentingan?updateSyerHakmilikPihak&idpihak=' + idpihak
                    + '&syer1=' + s1 + '&syer2=' + s2;
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

        function popupEditPihak(id, idH, idpbk) {            
            var url = "${pageContext.request.contextPath}/pihakBerkepentingan?showFormEdit&idPihak=" + id + "&idHakmilik=" + idH + "&idPBK=" + idpbk;
            window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
        }
        
        
        function cariWaris(id) {            
            var url = "${pageContext.request.contextPath}/daftar/pembetulan_pihak?cariWaris&idHakmilikPihakBerkepentingan=" + id;
            window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
        }

        function samaRata(f) {



            var q = $(f).formSerialize();
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
            $.get('${pageContext.request.contextPath}/daftar/pembetulan_pihak?agihSamaRata', q,
                    function(data) {
                        $('#page_div').html(data);
                        $.unblockUI();
                    });








        }

        function removePihakBerkepentingan(val,id) {
        <%-- alert(val);--%>
              var answer = confirm("adakah anda pasti untuk Hapus?");
              if (answer) {
                  var url = '${pageContext.request.contextPath}/daftar/pembetulan_pihak?deletePihakKepentingan&id_hP=' + val + '&idHakmilik=' + id;
                  $.blockUI({
                      message: $('#displayBox'),
                      css: {
                          top: ($(window).height() - 50) / 2 + 'px',
                          left: ($(window).width() - 50) / 2 + 'px',
                          width: '50px'
                      }
                  });
                  $.get(url,
                          function(data) {
                              $('#page_div').html(data);
                              $.unblockUI();
                          });
              }
          }

          function popupPihak(id) {
              alert("Penambahan pihak yang baru akan mengakibatkan perubahan keseluruhan syer.Sila semak syer selepas penambahan");
              var url = "${pageContext.request.contextPath}/pihakBerkepentingan?pihakKepentinganPopup&idHakmilik=" + id;
              window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=800");
              //maximizeWindow();
          }
          
          
            function reloadHakmilik(val) {               
               var url = '${pageContext.request.contextPath}/daftar/pembetulan_pihak?showForm&idHakmilik=' + val;
               $.blockUI({
                      message: $('#displayBox'),
                      css: {
                          top: ($(window).height() - 50) / 2 + 'px',
                          left: ($(window).width() - 50) / 2 + 'px',
                          width: '50px'
                      }
                  });
                  $.get(url,
                          function(data) {
                              $('#page_div').html(data);
                              $.unblockUI();
                          });

             }

    </script>

    <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
    <s:form beanclass="etanah.view.stripes.nota.pembetulanPihakActionBean" id="pembetulanPihak">
        <s:messages/>
        <s:errors/>
        <div class="subtitle">

            <fieldset class="aras1">
                <legend>Senarai Hakmilik Terlibat</legend>
                <p>
                    <label>Hakmilik :</label>
                    <s:select name="idHakmilik" onchange="reloadHakmilik(this.value);" id="hakmilik">
                        <c:forEach items="${actionBean.senaraiHakmilikTerlibat}" var="item" varStatus="line">
                            <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                                ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                            </s:option>
                        </c:forEach>
                    </s:select>
                </p>
                <br/>
            </fieldset>

            <fieldset class="aras1">
                <legend>Senarai Pihak Berkepentingan</legend>
                <div class="content" align="center" id="listpihak">
                    <display:table class="tablecloth" style="width:90%;" name="${actionBean.pihakKepentinganList}" cellpadding="0" cellspacing="0" id="linemohonpihak">
                        <%--<display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAllPihak(this);'>">
                          <s:checkbox name="checkboxpihak" id="checkboxpihak${linemohonpihak_rowNum-1}" value="${linemohonpihak.idHakmilikPihakBerkepentingan}" class="remove2"/>
                        </display:column>--%>
                        <display:column title="Bil" sortable="true">${linemohonpihak_rowNum}</display:column>
                        <display:column property="nama" title="Nama" style="text-transform:uppercase;"/>
                        <display:column property="noPengenalan" title="Nombor Pengenalan" />
                        <display:column property="jenis.nama" title="Jenis PB" />
                        <display:column property="aktif" title="Aktif" />                        
                        <display:column title="Bahagian yang diterima">
                            <%-- <s:text name="syerPembilang[${linemohonpihak_rowNum-1}]" size="5" class="number"/>
                             /
                             <s:text name="syerPenyebut[${linemohonpihak_rowNum-1}]" size="5" class="number"/>--%>
                            <%-- <c:if test="${actionBean.p.kodUrusan.kod eq 'HKABS'}">
                              <c:set var="disabledbtn" value="disabled"/>
                              <s:text name="syerPembilang[${linemohonpihak_rowNum-1}]" size="5" class="number" disabled="disabled"/>
                              /
                              <s:text name="syerPenyebut[${linemohonpihak_rowNum-1}]" size="5" class="number" disabled="disabled"/>

              </c:if>--%>


                            <div align="center">
                                <s:text name="syerPembilang[${linemohonpihak_rowNum-1}]" size="5" id="syer1${linemohonpihak_rowNum-1}"
                                        onblur="updateSyer('${linemohonpihak.idHakmilikPihakBerkepentingan}', '${linemohonpihak_rowNum-1}')"
                                        onchange="updateSyer('${linemohonpihak.idHakmilikPihakBerkepentingan}', '${linemohonpihak_rowNum-1}" class="pembilang"/> /
                                <s:text name="syerPenyebut[${linemohonpihak_rowNum-1}]" size="5" id="syer2${linemohonpihak_rowNum-1}"
                                        onblur="updateSyer('${linemohonpihak.idHakmilikPihakBerkepentingan}', '${linemohonpihak_rowNum-1}')"
                                        onchange="updateSyer('${linemohonpihak.idHakmilikPihakBerkepentingan}', '${linemohonpihak_rowNum-1}" class="penyebut"/>
                            </div>

                        </display:column>
                        <c:if test="${disabledbtn ne 'disabled'}">
                            <display:column title="Kemaskini">
                                <div align="center">
                                    <a href="#" onclick="popupEditPihak('${linemohonpihak.pihak.idPihak}', '${actionBean.hakmilik.idHakmilik}', '${linemohonpihak.idHakmilikPihakBerkepentingan}')">Kemaskini</a>
                                </div>
                            </display:column>
                            <%--<display:column title="Hapus">                
                                <div align="center">
                                    <img alt='Klik Untuk Padam' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                                         id='rem_${linepihak_rowNum}' onclick="removePihakBerkepentingan('${linemohonpihak.idHakmilikPihakBerkepentingan}', '${actionBean.hakmilik.idHakmilik}')" style="cursor:hand">
                                </div>
                            </display:column>--%>
                            <display:column title="Waris">
                                <c:if test="${linemohonpihak.jenis.kod eq 'PP' ||linemohonpihak.jenis.kod eq 'PA' ||linemohonpihak.jenis.kod eq 'PK'}">
                                     <div align="center">
                                    <img alt='Klik Untuk Tambah Waris' border='0' src='${pageContext.request.contextPath}/pub/images/tambah.png' class='rem'
                                         id='rem_${linepihak_rowNum}' onclick="cariWaris('${linemohonpihak.idHakmilikPihakBerkepentingan}')" style="cursor:hand">
                                </div>
                                </c:if>
                            </display:column>
                        </c:if>
                        <display:column title="ID Pihak" property="pihak.idPihak" class="hidden" headerClass="hidden" media="html"/>
                        <%--<s:hidden name="idpihak${line_rowNum}" class="idpihak${line_rowNum}" value="${actionBean.idPihak}" />--%>
                    </display:table>
                </div>

                <div align="center">
                    <s:button name="btnpopupPihak"  id="btnpopupPihak" disabled="${disabledbtn}"  value="Tambah" class="btn" onclick="popupPihak('${actionBean.hakmilik.idHakmilik}');" />
                    <c:if test="${fn:length(actionBean.pihakKepentinganList) > 0}">
                        <%--<s:button name="semak" disabled="${disabledbtn}" id="semak" value="Semak Syer" class="longbtn" onclick="semakSyer(this.form,'${actionBean.hakmilik.idHakmilik}');"/>--%>
                        <%--<s:button class="longbtn" value="Agih Sama Rata" name="agihSamaRata" id="agihSamaRata" onclick="samaRata(this.form);"/>&nbsp;--%>
                        <%--<s:button disabled="${disabledbtn}" class="longbtn" value="Agih Sama Rata" name="agihSamaRata" id="agihSamaRata" onclick="samaRata(this.form);"/>&nbsp;--%>
                    </c:if>
                    <s:button name="" value="Klik Untuk Refresh" class="longbtn"  onclick="reload('${actionBean.hakmilik.idHakmilik}')"/>

                </div>
            </fieldset>
        </div>



    </s:form>
</body>