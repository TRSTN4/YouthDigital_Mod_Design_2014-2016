import os.path, sys
import shutil
import urllib, json

def main(argv, forge_dir):
    if len(argv) < 2:
        print 'yd.py [all|downloadminecraft|fmllocal]'
        sys.exit(2)
    
    arg = argv[1]
    if (arg=='all'):
        download_minecraft(forge_dir)
        copy_fml_local(forge_dir)
    if (arg=='downloadminecraft'):
        download_minecraft(forge_dir)
    elif (arg=='fmllocal'):
        copy_fml_local(forge_dir)
        
#==========================================================================
#                      Download Minecraft support files! 
#==========================================================================    
def download_minecraft(forge_dir): 
    print '( ======== Downloading Minecraft Support Files ======== ) \n \n( ======== This may take some time to download! ======== ) \n '   
    yd_dir = os.path.join(forge_dir, 'yd')
    yd_downloaded_dir = os.path.join(yd_dir, 'downloaded')
    yd_json_file = os.path.join(yd_dir, 'yd.json')
    yd_json = None
    
    try:
        yd_json = json.load(open(yd_json_file))
    except Exception as e:
        print 'Failed to load yd json: %s' % yd_json_file
        sys.exit(1)    
    
    for lib in yd_json['libraries']:
        url = lib['url']
        filename = lib['filename']
        downloadpath = os.path.join(yd_downloaded_dir, filename)        
        if 'path' in lib.keys():
            path = lib['path'].split('/')
            downloaddir = os.path.join(yd_dir, os.sep.join(path))
            if not os.path.exists(downloaddir):
                os.makedirs(downloaddir)
            downloadpath = os.path.join(downloaddir, filename)        
		
        if not os.path.exists(downloadpath):
            print '\nDownloading %s \nfrom %s \nto %s) \n' % (filename,url,downloadpath)
            try:
                urllib.urlretrieve(url, downloadpath)
            except Exception as e:
                print e
                print '%sDownload of %s failed' % (url)
                return False
        else:
            print '	=) %s already downloaded.' % (filename)  				
    print ' \n( ======== Downloading Minecraft Support Files complete. ======== ) \n ' 

#==========================================================================
#                      Copy Downloaded Minecraft support files to FML dir 
#==========================================================================

def copy_fml_local(forge_dir):
    print 'Downloading Minecraft Support Files'  
    yd_dir = os.path.join(forge_dir, 'yd')
    fml_dir = os.path.join(forge_dir, 'fml')
    mcp_dir = os.path.join(forge_dir, 'mcp')
    
    sys.path.append(fml_dir)
    from fml import read_mc_versions, file_backup
    failed = False
    
    mcp_jars_dir=os.path.join(mcp_dir, 'jars')
    mc_info = read_mc_versions(fml_dir, None, work_dir=mcp_jars_dir)
        
    yd_libraries_dir = os.path.join(yd_dir, 'included', 'libraries') 
    yd_downloaded_dir = os.path.join(yd_dir, 'downloaded') 
    
    minecraft_client_file = os.path.join(yd_downloaded_dir, '1.6.4.jar') 
    minecraft_server_file = os.path.join(yd_downloaded_dir, 'minecraft_server.1.6.4.jar')
    mcp_file = os.path.join(yd_downloaded_dir, 'mcp811.zip')
    
    fml_json_file = os.path.join(fml_dir, 'fml.json')
        
    try:
        #copy all of yd includeded libraries directory
        print('Copying included libraries')
        shutil.rmtree(mc_info['library_dir']) 
        shutil.copytree(yd_libraries_dir, mc_info['library_dir'])

        #copy fml json file               
        shutil.copyfile(fml_json_file,mc_info['json_file'])     
        
        #copy downloaded client file
        print('Copying minecraft client file')       
        if (os.path.exists(minecraft_client_file) and not os.path.exists(mc_info['client_file'])):
            shutil.copyfile(minecraft_client_file,mc_info['client_file'])
        file_backup(mc_info['client_file'], None)        
        
        #copy or download server jar
        print('Copying minecraft server file')
        if (os.path.exists(minecraft_server_file) and not os.path.exists(mc_info['server_file'])):
            shutil.copyfile(minecraft_server_file,mc_info['server_file'])
        file_backup(mc_info['server_file'], None)
        
        print('Copying MCP zip file')
        if (os.path.exists(mcp_file) and not os.path.exists(mc_info['mcp_file'])):
            shutil.copyfile(mcp_file,mc_info['mcp_file'])
               
        
    except Exception as e: # python >2.5
        print 'Failed to copy .minecraft/[libraries/assets/versions] directory to %s' % mcp_jars_dir
        sys.exit(1)

  
if __name__ == '__main__':    
    forge_dir = os.path.abspath(os.path.join(os.path.dirname( __file__ ), '..', '..'))
    main(sys.argv, forge_dir)    
          

