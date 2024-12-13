


# create by Azizi 
# Nov 11 2013

To deploy different schema

Schema FAT
- By default, melaka.properties point to FAT schema.

Schema JASIN
1. Please rename melaka.properties to fat-melaka.properties.
2. Rename jasin-melaka.properties to melaka.properties.
3. Double check the schema login and pw by using cat.
4. Deploy as usual but monitor the meta-data log to confirm correct schema.
5. Rename back the properties file to their original name.

Schema JASIN3
1. Please rename melaka.properties to fat-melaka.properties.
2. Rename jasin3-melaka.properties to melaka.properties.
3. Double check the schema login and pw by using cat.
4. Deploy as usual but monitor the meta-data log to confirm correct schema.
5. Rename back the properties file to their original name.

Schema ETMLAPP
- Please use pat folder as default by using pat-melaka.properties.



***** WAR FILES Directories *****
FAT
- uploads_fat --> instance etanah_fat -> server etanah_testing

ETMLAPP 
- uploads_pat --> instance etanah_pat -> server etanah_pat

JASIN
- uploads_testing --> instace etanah_testing -> server testing

JASIN3 
- uploads --> instance etanah -> server app_server1





